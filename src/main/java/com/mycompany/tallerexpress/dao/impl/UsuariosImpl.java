package com.mycompany.tallerexpress.dao.impl;

import com.mycompany.tallerexpress.config.DatabaseConnection;
import com.mycompany.tallerexpress.dao.UsuariosDao;
import com.mycompany.tallerexpress.model.Usuarios;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuariosImpl implements UsuariosDao {

    @Override
    public Usuarios create(Usuarios u) throws Exception {

        String sql = "INSERT INTO usuarios "
                + "(nombre_usuario, contrasena, rol, estado) "
                + "VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            ps.setString(
                    4,
                    u.getEstado() != null
                            ? u.getEstado()
                            : "ACTIVO"
            );

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    u.setId(rs.getInt(1));
                }
            }
        }

        return u;
    }

    @Override
    public List<Usuarios> findAll() throws Exception {

        List<Usuarios> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Usuarios u = new Usuarios();

                u.setId(rs.getInt("id"));
                u.setUsername(
                        rs.getString("nombre_usuario"));
                u.setPassword(
                        rs.getString("contrasena"));
                u.setRole(
                        rs.getString("rol"));
                u.setEstado(
                        rs.getString("estado"));
                u.setCreatedAt(
                        rs.getTimestamp("created_at"));

                lista.add(u);
            }
        }

        return lista;
    }

    @Override
    public Optional<Usuarios> findById(Integer id) throws Exception {

        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Usuarios u = new Usuarios();

                    u.setId(rs.getInt("id"));
                    u.setUsername(
                            rs.getString("nombre_usuario"));
                    u.setPassword(
                            rs.getString("contrasena"));
                    u.setRole(
                            rs.getString("rol"));
                    u.setEstado(
                            rs.getString("estado"));
                    u.setCreatedAt(
                            rs.getTimestamp("created_at"));

                    return Optional.of(u);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Usuarios> findByUsername(
            String username) throws Exception {

        String sql = "SELECT * FROM usuarios "
                + "WHERE nombre_usuario = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Usuarios u = new Usuarios();

                    u.setId(rs.getInt("id"));
                    u.setUsername(
                            rs.getString("nombre_usuario"));
                    u.setPassword(
                            rs.getString("contrasena"));
                    u.setRole(
                            rs.getString("rol"));
                    u.setEstado(
                            rs.getString("estado"));
                    u.setCreatedAt(
                            rs.getTimestamp("created_at"));

                    return Optional.of(u);
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public void update(Usuarios u) throws Exception {

        String sql = "UPDATE usuarios SET "
                + "nombre_usuario = ?, "
                + "contrasena = ?, "
                + "rol = ?, "
                + "estado = ? "
                + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getRole());
            ps.setString(4, u.getEstado());
            ps.setInt(5, u.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {

        String sql = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}