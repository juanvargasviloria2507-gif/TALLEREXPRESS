/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service.impl;

import com.mycompany.tallerexpress.dao.VehiculosDao;
import com.mycompany.tallerexpress.dao.impl.VehiculosImpl;
import com.mycompany.tallerexpress.exception.PersistenceException;
import com.mycompany.tallerexpress.model.Vehiculos;
import com.mycompany.tallerexpress.service.VehiculosService;
import com.mycompany.tallerexpress.util.HttpLogger;

import java.util.List;

public class VehiculosServiceImpl implements VehiculosService {
    private final VehiculosDao vehiculosDao = new VehiculosImpl();

    @Override
    public Vehiculos registrar(Vehiculos vehiculo) throws Exception {
        HttpLogger.log("POST", "/api/vehiculos", 201, "Registrando vehículo");
        if (vehiculosDao.findByPlaca(vehiculo.getPlaca()).isPresent()) {
            throw new PersistenceException("La placa " + vehiculo.getPlaca() + " ya está registrada en el sistema.");
        }
        return vehiculosDao.create(vehiculo);
    }

    @Override
    public List<Vehiculos> listarPorCliente(Integer clienteId) throws Exception {
        HttpLogger.log("GET", "/api/vehiculos/cliente/" + clienteId, 200, "Consultando vehículos por cliente");
        return vehiculosDao.findByClienteId(clienteId);
    }
}