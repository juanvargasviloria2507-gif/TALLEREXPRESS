
package com.mycompany.tallerexpress.dao.impl;

import com.mycompany.tallerexpress.config.DatabaseConnection;
import com.mycompany.tallerexpress.dao.ClienteDao;
import com.mycompany.tallerexpress.model.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesImpl implements ClienteDao {

    @Override
    public Cliente create(Cliente cliente) throws Exception {

        String sql = "INSERT INTO clientes "
                + "(tipo_identificacion, numero_identificacion, nombre_completo, "
                + "telefono, correo, direccion, estado, fecha_registro) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getTipoIdentificacion());
            ps.setString(2, cliente.getNumeroIdentificacion());
            ps.setString(3, cliente.getNombreCompleto());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getDireccion());
            ps.setString(7, cliente.getEstado());
            ps.setDate(8, cliente.getFechaRegistro());

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    cliente.setId(rs.getInt(1));
                }
            }
        }

        return cliente;
    }

    @Override
    public List<Cliente> findAll() throws Exception {

        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT * FROM clientes";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente c = new Cliente();

                c.setId(rs.getInt("id"));
                c.setTipoIdentificacion(
                        rs.getString("tipo_identificacion"));
                c.setNumeroIdentificacion(
                        rs.getString("numero_identificacion"));
                c.setNombreCompleto(
                        rs.getString("nombre_completo"));
                c.setTelefono(
                        rs.getString("telefono"));
                c.setCorreo(
                        rs.getString("correo"));
                c.setDireccion(
                        rs.getString("direccion"));
                c.setEstado(
                        rs.getString("estado"));
                c.setFechaRegistro(
                        rs.getDate("fecha_registro"));
                c.setCreatedAt(
                        rs.getTimestamp("created_at"));

                lista.add(c);
            }
        }

        return lista;
    }

    @Override
    public Cliente findById(Integer id) throws Exception {

        String sql = "SELECT * FROM clientes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Cliente c = new Cliente();

                    c.setId(rs.getInt("id"));
                    c.setTipoIdentificacion(
                            rs.getString("tipo_identificacion"));
                    c.setNumeroIdentificacion(
                            rs.getString("numero_identificacion"));
                    c.setNombreCompleto(
                            rs.getString("nombre_completo"));
                    c.setTelefono(
                            rs.getString("telefono"));
                    c.setCorreo(
                            rs.getString("correo"));
                    c.setDireccion(
                            rs.getString("direccion"));
                    c.setEstado(
                            rs.getString("estado"));
                    c.setFechaRegistro(
                            rs.getDate("fecha_registro"));
                    c.setCreatedAt(
                            rs.getTimestamp("created_at"));

                    return c;
                }
            }
        }

        return null;
    }

    @Override
    public void update(Cliente cliente) throws Exception {

        String sql = "UPDATE clientes SET "
                + "tipo_identificacion = ?, "
                + "numero_identificacion = ?, "
                + "nombre_completo = ?, "
                + "telefono = ?, "
                + "correo = ?, "
                + "direccion = ?, "
                + "estado = ? "
                + "WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cliente.getTipoIdentificacion());
            ps.setString(2, cliente.getNumeroIdentificacion());
            ps.setString(3, cliente.getNombreCompleto());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getCorreo());
            ps.setString(6, cliente.getDireccion());
            ps.setString(7, cliente.getEstado());
            ps.setInt(8, cliente.getId());

            ps.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {

        String sql = "DELETE FROM clientes WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}