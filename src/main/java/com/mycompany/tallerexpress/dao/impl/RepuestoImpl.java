/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        String sql = "INSERT INTO repuestos (codigo_referencia, nombre, presentacion, proveedor, stock_total, stock_disponible, precio_unitario) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, r.getCodigoReferencia());
            ps.setString(2, r.getNombre());
            ps.setString(3, r.getPresentacion());
            ps.setString(4, r.getProveedor());
            ps.setInt(5, r.getStockTotal());
            ps.setInt(6, r.getStockDisponible());
            ps.setDouble(7, r.getPrecioUnitario());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) r.setId(rs.getInt(1));
            }
            return r;
        }
    }

    @Override
    public void update(Repuesto r) throws Exception {
        String sql = "UPDATE repuestos SET nombre = ?, presentacion = ?, proveedor = ?, stock_total = ?, stock_disponible = ?, precio_unitario = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, r.getNombre());
            ps.setString(2, r.getPresentacion());
            ps.setString(3, r.getProveedor());
            ps.setInt(4, r.getStockTotal());
            ps.setInt(5, r.getStockDisponible());
            ps.setDouble(6, r.getPrecioUnitario());
            ps.setInt(7, r.getId());
            ps.executeUpdate();
        }
    }

    @Override
    public List<Repuesto> findAll() throws Exception {
        return findByCategoriaOrProveedor("");
    }

    @Override
    public List<Repuesto> findByCategoriaOrProveedor(String filtro) throws Exception {
        List<Repuesto> lista = new ArrayList<>();
        String sql = "SELECT * FROM repuestos WHERE LOWER(presentacion) LIKE ? OR LOWER(proveedor) LIKE ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String pattern = "%" + filtro.toLowerCase() + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Repuesto r = new Repuesto();
                    r.setId(rs.getInt("id"));
                    r.setCodigoReferencia(rs.getString("codigo_referencia"));
                    r.setNombre(rs.getString("nombre"));
                    r.setPresentacion(rs.getString("presentacion"));
                    r.setProveedor(rs.getString("proveedor"));
                    r.setStockTotal(rs.getInt("stock_total"));
                    r.setStockDisponible(rs.getInt("stock_disponible"));
                    r.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    lista.add(r);
                }
            }
        }
        return lista;
    }
}
