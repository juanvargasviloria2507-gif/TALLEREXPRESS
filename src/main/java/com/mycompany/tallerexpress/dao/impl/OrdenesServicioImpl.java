/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.dao.impl;

import com.mycompany.tallerexpress.config.DatabaseConnection;
import com.mycompany.tallerexpress.dao.OrdenesServicioDao;
import com.mycompany.tallerexpress.exception.PersistenceException;
import com.mycompany.tallerexpress.model.OrdenesServicio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenesServicioImpl implements OrdenesServicioDao {

    @Override
    public OrdenesServicio registrarOrdenTransaccional(OrdenesServicio orden, Integer repuestoId, Integer cantidad) throws Exception {
        String sqlOrden = "INSERT INTO ordenes_de_servicio (cliente_id, vehiculo_id, repuesto_utilizado, mecanico_responsable, descripcion_problema, diagnostico, estado_de_la_orden) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String sqlStock = "UPDATE repuestos SET stock_disponible = stock_disponible - ? WHERE id = ? AND stock_disponible >= ?";

        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement psOrden = conn.prepareStatement(sqlOrden, Statement.RETURN_GENERATED_KEYS)) {
                psOrden.setInt(1, orden.getClienteId());
                psOrden.setInt(2, orden.getVehiculoId());
                
                if (repuestoId != null && repuestoId > 0) {
                    psOrden.setInt(3, repuestoId);
                } else {
                    psOrden.setNull(3, Types.INTEGER);
                }

                psOrden.setString(4, orden.getMecanico());
                psOrden.setString(5, orden.getDescripcionProblema());
                psOrden.setString(6, orden.getDiagnostico());
                psOrden.setString(7, orden.getEstado() != null ? orden.getEstado() : "PROGRAMADA");
                psOrden.executeUpdate();

                try (ResultSet rs = psOrden.getGeneratedKeys()) {
                    if (rs.next()) orden.setId(rs.getInt(1));
                }
            }

            if (repuestoId != null && repuestoId > 0 && cantidad != null && cantidad > 0) {
                try (PreparedStatement psStock = conn.prepareStatement(sqlStock)) {
                    psStock.setInt(1, cantidad);
                    psStock.setInt(2, repuestoId);
                    psStock.setInt(3, cantidad);
                    if (psStock.executeUpdate() == 0) {
                        throw new PersistenceException("Stock insuficiente para el repuesto.");
                    }
                }
            }

            conn.commit();
            return orden;

        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw new PersistenceException("Error en la transacción de registro: " + e.getMessage(), e);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public void actualizarEstadoYCostoTransaccional(Integer ordenId, String nuevoEstado, Double costoTotal) throws Exception {
        String sql = "UPDATE ordenes_de_servicio SET estado_de_la_orden = ?, costo_total = ? WHERE id = ?";
        Connection conn = null;
        try {
            conn = DatabaseConnection.getConnection();
            conn.setAutoCommit(false);

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, nuevoEstado);
                ps.setDouble(2, costoTotal);
                ps.setInt(3, ordenId);
                
                int rows = ps.executeUpdate();
                if (rows == 0) {
                    throw new PersistenceException("Orden de servicio no encontrada.");
                }
            }

            conn.commit();
        } catch (Exception e) {
            if (conn != null) conn.rollback();
            throw new PersistenceException("Error al actualizar estado y costo de la orden", e);
        } finally {
            if (conn != null) {
                conn.setAutoCommit(true);
                conn.close();
            }
        }
    }

    @Override
    public List<OrdenesServicio> findByVehiculo(Integer vehiculoId) throws Exception {
        List<OrdenesServicio> lista = new ArrayList<>();
        String sql = "SELECT * FROM ordenes_de_servicio WHERE vehiculo_id = ? ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, vehiculoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrdenesServicio o = new OrdenesServicio();
                    o.setId(rs.getInt("id"));
                    o.setClienteId(rs.getInt("cliente_id"));
                    o.setVehiculoId(rs.getInt("vehiculo_id"));
                    o.setMecanico(rs.getString("mecanico_responsable"));
                    o.setDescripcionProblema(rs.getString("descripcion_problema"));
                    o.setDiagnostico(rs.getString("diagnostico"));
                    o.setEstado(rs.getString("estado_de_la_orden"));
                    o.setCostoTotal(rs.getBigDecimal("costo_total"));
                    lista.add(o);
                }
            }
        }
        return lista;
    }
}