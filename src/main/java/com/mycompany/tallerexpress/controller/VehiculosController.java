/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.model.Vehiculos;
import com.mycompany.tallerexpress.service.VehiculosService;
import com.mycompany.tallerexpress.service.impl.VehiculosServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author coder
 */
public class VehiculosController {

    private final VehiculosService vehiculosService = new VehiculosServiceImpl();

    public Vehiculos registrarVehiculo(Vehiculos vehiculo) throws Exception {
        return vehiculosService.create(vehiculo);
    }

    public Optional<Vehiculos> buscarVehiculoPorId(Integer id) throws Exception {
        return vehiculosService.findById(id);
    }

    public Optional<Vehiculos> buscarVehiculoPorPlaca(String placa) throws Exception {
        return vehiculosService.findByPlaca(placa);
    }

    public List<Vehiculos> listarVehiculosPorCliente(Integer clienteId) throws Exception {
        return vehiculosService.findByClienteId(clienteId);
    }

    public List<Vehiculos> listarVehiculos() throws Exception {
        return vehiculosService.findAll();
    }

    public void actualizarVehiculo(Vehiculos vehiculo) throws Exception {
        vehiculosService.update(vehiculo);
    }

    public void eliminarVehiculo(Integer id) throws Exception {
        vehiculosService.delete(id);
    }
}