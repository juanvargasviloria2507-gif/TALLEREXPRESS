package com.mycompany.tallerexpress.dao.impl;

import com.mycompany.tallerexpress.config.DatabaseConnection;
import com.mycompany.tallerexpress.dao.VehiculosDao;
import com.mycompany.tallerexpress.model.Vehiculos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehiculosImpl implements VehiculosDao {

    @Override
    public Vehiculos create(Vehiculos v) throws Exception {

        String sql = "INSERT INTO vehiculos "
                + "(placa, marca, modelo, año, cliente_id, estado) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getAño());
            ps.setInt(5, v.getClienteId());
            ps.setString(6, v.getEstado());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {

                if (rs.next()) {
                    v.setId(rs.getInt(1));
                }
            }
        }

        return v;
    }

    @Override
    public Optional<Vehiculos> findById(Integer id) throws Exception {

        String sql = "SELECT * FROM vehiculos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapearVehiculo(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public Optional<Vehiculos> findByPlaca(String placa) throws Exception {

        String sql = "SELECT * FROM vehiculos WHERE placa = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, placa);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(mapearVehiculo(rs));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public List<Vehiculos> findByClienteId(Integer clienteId)
            throws Exception {

        List<Vehiculos> lista = new ArrayList<>();

        String sql = "SELECT * FROM vehiculos "
                + "WHERE cliente_id = ? "
                + "ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, clienteId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    lista.add(mapearVehiculo(rs));
                }
            }
        }

        return lista;
    }

    @Override
    public List<Vehiculos> findAll() throws Exception {

        List<Vehiculos> lista = new ArrayList<>();

        String sql = "SELECT * FROM vehiculos ORDER BY id DESC";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearVehiculo(rs));
            }
        }

        return lista;
    }

    @Override
    public void update(Vehiculos v) throws Exception {

        String sql = "UPDATE vehiculos SET "
                + "placa = ?, "
                + "marca = ?, "
                + "modelo = ?, "
                + "año = ?, "
                + "cliente_id = ?, "
                + "estado = ? "
                + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getAño());
            ps.setInt(5, v.getClienteId());
            ps.setString(6, v.getEstado());
            ps.setInt(7, v.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {

        String sql = "DELETE FROM vehiculos WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();
        }
    }

    private Vehiculos mapearVehiculo(ResultSet rs)
            throws SQLException {

        Vehiculos v = new Vehiculos();

        v.setId(rs.getInt("id"));
        v.setPlaca(rs.getString("placa"));
        v.setMarca(rs.getString("marca"));
        v.setModelo(rs.getString("modelo"));
        v.setAño(rs.getInt("año"));
        v.setClienteId(rs.getInt("cliente_id"));
        v.setEstado(rs.getString("estado"));
        v.setFechaRegistro(rs.getDate("fecha_registro"));
        v.setCreatedAt(rs.getTimestamp("created_at"));

        return v;
    }
}