package com.mycompany.tallerexpress.dao.impl;

import com.mycompany.tallerexpress.config.DatabaseConnection;
import com.mycompany.tallerexpress.dao.OrdenesServicioDao;
import com.mycompany.tallerexpress.exception.PersistenceException;
import com.mycompany.tallerexpress.model.OrdenesServicio;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenesServicioImpl implements OrdenesServicioDao {

    @Override
    public OrdenesServicio registrarOrdenTransaccional(
            OrdenesServicio orden,
            Integer repuestoId,
            Integer cantidad) throws Exception {

        String sqlOrden = "INSERT INTO ordenes_de_servicio "
                + "(cliente_id, vehiculo_id, repuesto_utilizado, "
                + "mecanico_responsable, fecha, descripcion_problema, "
                + "diagnostico, estado_de_la_orden) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String sqlStock = "UPDATE repuestos "
                + "SET stock_disponible = stock_disponible - ? "
                + "WHERE id = ? AND stock_disponible >= ?";

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement psOrden = conn.prepareStatement(
                    sqlOrden,
                    Statement.RETURN_GENERATED_KEYS)) {

                psOrden.setInt(1, orden.getClienteId());
                psOrden.setInt(2, orden.getVehiculoId());

                if (repuestoId != null && repuestoId > 0) {
                    psOrden.setInt(3, repuestoId);
                } else {
                    psOrden.setNull(3, Types.INTEGER);
                }

                psOrden.setString(4, orden.getMecanicoResponsable());

                if (orden.getFecha() != null) {
                    psOrden.setDate(5, orden.getFecha());
                } else {
                    psOrden.setDate(5, new Date(System.currentTimeMillis()));
                }

                psOrden.setString(6, orden.getDescripcionProblema());
                psOrden.setString(7, orden.getDiagnostico());

                psOrden.setString(
                        8,
                        orden.getEstadoDeLaOrden() != null
                                ? orden.getEstadoDeLaOrden()
                                : "PROGRAMADA"
                );

                psOrden.executeUpdate();

                try (ResultSet rs = psOrden.getGeneratedKeys()) {
                    if (rs.next()) {
                        orden.setId(rs.getInt(1));
                    }
                }
            }

            /*
             * Si se utilizó un repuesto, se descuenta del
             * stock disponible dentro de la misma transacción.
             */
            if (repuestoId != null
                    && repuestoId > 0
                    && cantidad != null
                    && cantidad > 0) {

                try (PreparedStatement psStock =
                             conn.prepareStatement(sqlStock)) {

                    psStock.setInt(1, cantidad);
                    psStock.setInt(2, repuestoId);
                    psStock.setInt(3, cantidad);

                    if (psStock.executeUpdate() == 0) {
                        throw new PersistenceException(
                                "Stock insuficiente para el repuesto."
                        );
                    }
                }
            }

            conn.commit();

            return orden;

        } catch (Exception e) {

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }
            }

            throw new PersistenceException(
                    "Error en la transacción de registro: "
                    + e.getMessage(),
                    e
            );

        } finally {

            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } finally {
                    conn.close();
                }
            }
        }
    }

    @Override
    public void actualizarEstadoYCostoTransaccional(
            Integer ordenId,
            String nuevoEstado,
            BigDecimal costoTotal) throws Exception {

        String sql = "UPDATE ordenes_de_servicio "
                + "SET estado_de_la_orden = ?, costo_total = ? "
                + "WHERE id = ?";

        Connection conn = null;

        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {

                ps.setString(1, nuevoEstado);
                ps.setBigDecimal(2, costoTotal);
                ps.setInt(3, ordenId);

                int rows = ps.executeUpdate();

                if (rows == 0) {
                    throw new PersistenceException(
                            "Orden de servicio no encontrada."
                    );
                }
            }

            conn.commit();

        } catch (Exception e) {

            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackException) {
                    e.addSuppressed(rollbackException);
                }
            }

            throw new PersistenceException(
                    "Error al actualizar estado y costo de la orden",
                    e
            );

        } finally {

            if (conn != null) {
                try {
                    conn.setAutoCommit(true);
                } finally {
                    conn.close();
                }
            }
        }
    }

    @Override
    public List<OrdenesServicio> findByVehiculo(
            Integer vehiculoId) throws Exception {

        List<OrdenesServicio> lista = new ArrayList<>();

        String sql = "SELECT * FROM ordenes_de_servicio "
                + "WHERE vehiculo_id = ? "
                + "ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, vehiculoId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    OrdenesServicio o = new OrdenesServicio();

                    o.setId(rs.getInt("id"));
                    o.setClienteId(rs.getInt("cliente_id"));
                    o.setVehiculoId(rs.getInt("vehiculo_id"));

                    int repuestoId = rs.getInt("repuesto_utilizado");

                    if (rs.wasNull()) {
                        o.setRepuestoUtilizado(null);
                    } else {
                        o.setRepuestoUtilizado(repuestoId);
                    }

                    o.setMecanicoResponsable(
                            rs.getString("mecanico_responsable")
                    );

                    o.setFecha(
                            rs.getDate("fecha")
                    );

                    o.setDescripcionProblema(
                            rs.getString("descripcion_problema")
                    );

                    o.setDiagnostico(
                            rs.getString("diagnostico")
                    );

                    o.setEstadoDeLaOrden(
                            rs.getString("estado_de_la_orden")
                    );

                    o.setCostoTotal(
                            rs.getBigDecimal("costo_total")
                    );

                    o.setFechaCreacion(
                            rs.getTimestamp("fecha_creacion")
                    );

                    lista.add(o);
                }
            }
        }

        return lista;
    }
}