/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service.impl;

import com.mycompany.tallerexpress.dao.OrdenesServicioDao;
import com.mycompany.tallerexpress.dao.impl.OrdenesServicioImpl;
import com.mycompany.tallerexpress.exception.BusinessException;
import com.mycompany.tallerexpress.model.OrdenesServicio;
import com.mycompany.tallerexpress.service.OrdenesServicioService;
import com.mycompany.tallerexpress.util.HttpLogger;

import java.util.List;

public class OrdenesServicioServiceImpl implements OrdenesServicioService {

    private final OrdenesServicioDao ordenesDao = new OrdenesServicioImpl();

    @Override
    public OrdenesServicio registrarOrden(OrdenesServicio orden, Integer repuestoId, Integer cantidad) throws Exception {
        HttpLogger.log("POST", "/api/ordenes", 201, "Registrando orden de servicio");

        if (orden.getClienteId() == null || orden.getClienteId() <= 0) {
            throw new BusinessException("El ID del cliente es obligatorio.");
        }
        if (orden.getVehiculoId() == null || orden.getVehiculoId() <= 0) {
            throw new BusinessException("El ID del vehículo es obligatorio.");
        }
        if (orden.getMecanico() == null || orden.getMecanico().isBlank()) {
            throw new BusinessException("Debe asignar un mecánico responsable.");
        }

        return ordenesDao.registrarOrdenTransaccional(orden, repuestoId, cantidad);
    }

    @Override
    public void actualizarEstadoYCosto(Integer ordenId, String nuevoEstado, Double costoTotal) throws Exception {
        HttpLogger.log("PATCH", "/api/ordenes/" + ordenId, 200, "Actualizando estado y costo");

        if (ordenId == null || ordenId <= 0) {
            throw new BusinessException("ID de orden inválido.");
        }
        if (nuevoEstado == null || nuevoEstado.isBlank()) {
            throw new BusinessException("El nuevo estado es obligatorio.");
        }
        if (costoTotal == null || costoTotal < 0) {
            throw new BusinessException("El costo total no puede ser negativo.");
        }

        ordenesDao.actualizarEstadoYCostoTransaccional(ordenId, nuevoEstado, costoTotal);
    }

    @Override
    public List<OrdenesServicio> listarPorVehiculo(Integer vehiculoId) throws Exception {
        HttpLogger.log("GET", "/api/ordenes/vehiculo/" + vehiculoId, 200, "Consultando historial");

        if (vehiculoId == null || vehiculoId <= 0) {
            throw new BusinessException("ID de vehículo inválido.");
        }

        return ordenesDao.findByVehiculo(vehiculoId);
    }
}