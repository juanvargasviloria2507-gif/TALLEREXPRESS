package com.mycompany.tallerexpress.dao.impl;

import com.mycompany.tallerexpress.config.DatabaseConnection;
import com.mycompany.tallerexpress.dao.RepuestoDao;
import com.mycompany.tallerexpress.model.Repuesto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepuestoImpl implements RepuestoDao {

    @Override
    public Repuesto create(Repuesto r) throws Exception {

        String sql = "INSERT INTO repuestos "
                + "(codigo_referencia, nombre, categoria, presentacion, "
                + "proveedor, stock_total, stock_disponible, precio_unitario, estado, fecha_registro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, r.getCodigoReferencia());
            ps.setString(2, r.getNombre());
            ps.setString(3, r.getCategoria());
            ps.setString(4, r.getPresentacion());
            ps.setString(5, r.getProveedor());
            ps.setInt(6, r.getStockTotal());
            ps.setInt(7, r.getStockDisponible());
            ps.setDouble(8, r.getPrecioUnitario());
            ps.setString(9, r.getEstado());
            ps.setDate(10, r.getFechaRegistro());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    r.setId(rs.getInt(1));
                }
            }
        }

        return r;
    }

    @Override
    public void update(Repuesto r) throws Exception {

        String sql = "UPDATE repuestos SET "
                + "codigo_referencia = ?, "
                + "nombre = ?, "
                + "categoria = ?, "
                + "presentacion = ?, "
                + "proveedor = ?, "
                + "stock_total = ?, "
                + "stock_disponible = ?, "
                + "precio_unitario = ?, "
                + "estado = ? "
                + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, r.getCodigoReferencia());
            ps.setString(2, r.getNombre());
            ps.setString(3, r.getCategoria());
            ps.setString(4, r.getPresentacion());
            ps.setString(5, r.getProveedor());
            ps.setInt(6, r.getStockTotal());
            ps.setInt(7, r.getStockDisponible());
            ps.setDouble(8, r.getPrecioUnitario());
            ps.setString(9, r.getEstado());
            ps.setInt(10, r.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public List<Repuesto> findAll() throws Exception {
        return findByCategoriaOrProveedor("");
    }

    @Override
    public Repuesto findById(Integer id) throws Exception {

        String sql = "SELECT * FROM repuestos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Repuesto r = new Repuesto();

                    r.setId(rs.getInt("id"));
                    r.setCodigoReferencia(
                            rs.getString("codigo_referencia"));
                    r.setNombre(
                            rs.getString("nombre"));
                    r.setCategoria(
                            rs.getString("categoria"));
                    r.setPresentacion(
                            rs.getString("presentacion"));
                    r.setProveedor(
                            rs.getString("proveedor"));
                    r.setStockTotal(
                            rs.getInt("stock_total"));
                    r.setStockDisponible(
                            rs.getInt("stock_disponible"));
                    r.setPrecioUnitario(
                            rs.getDouble("precio_unitario"));
                    r.setEstado(
                            rs.getString("estado"));
                    r.setFechaRegistro(
                            rs.getDate("fecha_registro"));
                    r.setCreatedAt(
                            rs.getTimestamp("created_at"));

                    return r;
                }
            }
        }

        return null;
    }

    @Override
    public List<Repuesto> findByCategoriaOrProveedor(
            String filtro) throws Exception {

        List<Repuesto> lista = new ArrayList<>();

        String sql = "SELECT * FROM repuestos "
                + "WHERE LOWER(categoria) LIKE ? "
                + "OR LOWER(proveedor) LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String pattern = "%" + filtro.toLowerCase() + "%";

            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Repuesto r = new Repuesto();

                    r.setId(rs.getInt("id"));
                    r.setCodigoReferencia(
                            rs.getString("codigo_referencia"));
                    r.setNombre(
                            rs.getString("nombre"));
                    r.setCategoria(
                            rs.getString("categoria"));
                    r.setPresentacion(
                            rs.getString("presentacion"));
                    r.setProveedor(
                            rs.getString("proveedor"));
                    r.setStockTotal(
                            rs.getInt("stock_total"));
                    r.setStockDisponible(
                            rs.getInt("stock_disponible"));
                    r.setPrecioUnitario(
                            rs.getDouble("precio_unitario"));
                    r.setEstado(
                            rs.getString("estado"));
                    r.setFechaRegistro(
                            rs.getDate("fecha_registro"));
                    r.setCreatedAt(
                            rs.getTimestamp("created_at"));

                    lista.add(r);
                }
            }
        }

        return lista;
    }

    @Override
    public void delete(Integer id) throws Exception {

        String sql = "DELETE FROM repuestos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}