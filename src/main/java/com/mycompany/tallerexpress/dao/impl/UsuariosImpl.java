/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.dao.impl;

import com.mycompany.tallerexpress.config.DatabaseConnection;
import com.mycompany.tallerexpress.dao.UsuariosDao;
import com.mycompany.tallerexpress.model.Usuarios;

import java.sql.*;
import java.util.Optional;

public class UsuariosImpl implements UsuariosDao {

    @Override
    public Usuarios create(Usuarios u) throws Exception {
        // SQL ajustado a los nombres de columnas de PostgreSQL
        String sql = "INSERT INTO usuarios (nombre_usuario, contrasena, rol, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            ps.setString(4, u.getEstado() != null ? u.getEstado() : "ACTIVO");
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                }
            }
            return u;
        }
    }

    @Override
    public Optional<Usuarios> findByUsername(String username) throws Exception {
        // Consulta SQL ajustada para PostgreSQL
        String sql = "SELECT * FROM usuarios WHERE nombre_usuario = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuarios u = new Usuarios();
                    u.setId(rs.getInt("id"));
                    u.setUsername(rs.getString("nombre_usuario"));
                    u.setPassword(rs.getString("contrasena"));
                    u.setRole(rs.getString("rol"));
                    u.setEstado(rs.getString("estado"));
                    return Optional.of(u);
                }
            }
        }
        return Optional.empty();
    }
}
