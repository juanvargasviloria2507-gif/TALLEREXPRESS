/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        String sql = "INSERT INTO clientes (documento, nombre, telefono, email, is_activo, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getDocumento());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setBoolean(5, cliente.getIsActivo() != null ? cliente.getIsActivo() : true);
            ps.setTimestamp(6, cliente.getCreatedAt() != null ? cliente.getCreatedAt() : new Timestamp(System.currentTimeMillis()));

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
                c.setDocumento(rs.getString("documento"));
                c.setNombre(rs.getString("nombre"));
                c.setTelefono(rs.getString("telefono"));
                c.setEmail(rs.getString("email"));
                c.setIsActivo(rs.getBoolean("is_activo"));
                c.setCreatedAt(rs.getTimestamp("created_at"));
                lista.add(c);
            }
        }
        return lista;
    }
}


