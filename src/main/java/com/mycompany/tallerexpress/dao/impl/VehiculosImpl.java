/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.dao.impl;

import com.mycompany.tallerexpress.config.DatabaseConnection;
import com.mycompany.tallerexpress.dao.VehiculosDao;
import com.mycompany.tallerexpress.model.Vehiculos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehiculosImpl implements VehiculosDao {

    @Override
    public Vehiculos create(Vehiculos v) throws Exception {
        String sql = "INSERT INTO vehiculos (placa, marca, modelo, anio, cliente_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getAnio());
            ps.setInt(5, v.getClienteId());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) v.setId(rs.getInt(1));
            }
            return v;
        }
    }

    @Override
    public Optional<Vehiculos> findByPlaca(String placa) throws Exception {
        String sql = "SELECT * FROM vehiculos WHERE placa = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Vehiculos v = new Vehiculos();
                    v.setId(rs.getInt("id"));
                    v.setPlaca(rs.getString("placa"));
                    v.setMarca(rs.getString("marca"));
                    v.setModelo(rs.getString("modelo"));
                    v.setAnio(rs.getInt("anio"));
                    v.setClienteId(rs.getInt("cliente_id"));
                    return Optional.of(v);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Vehiculos> findByClienteId(Integer clienteId) throws Exception {
        List<Vehiculos> list = new ArrayList<>();
        String sql = "SELECT * FROM vehiculos WHERE cliente_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, clienteId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Vehiculos v = new Vehiculos();
                    v.setId(rs.getInt("id"));
                    v.setPlaca(rs.getString("placa"));
                    v.setMarca(rs.getString("marca"));
                    v.setModelo(rs.getString("modelo"));
                    v.setAnio(rs.getInt("anio"));
                    v.setClienteId(rs.getInt("cliente_id"));
                    list.add(v);
                }
            }
        }
        return list;
    }
}