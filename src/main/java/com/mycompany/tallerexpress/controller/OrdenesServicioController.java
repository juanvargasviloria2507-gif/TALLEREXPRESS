/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.model.OrdenesServicio;
import com.mycompany.tallerexpress.service.OrdenesServicioService;
import com.mycompany.tallerexpress.service.impl.OrdenesServicioServiceImpl;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Coder
 */
public class OrdenesServicioController {

    private final OrdenesServicioService ordenesServicioService =
            new OrdenesServicioServiceImpl();

    public OrdenesServicio registrarOrden(
            OrdenesServicio orden,
            Integer repuestoId,
            Integer cantidad
    ) throws Exception {

        return ordenesServicioService.registrarOrdenTransaccional(
                orden,
                repuestoId,
                cantidad
        );
    }

    public List<OrdenesServicio> consultarHistorialPorVehiculo(
            Integer vehiculoId
    ) throws Exception {

        return ordenesServicioService.findByVehiculo(vehiculoId);
    }

    public void actualizarEstadoYCosto(
            Integer ordenId,
            String nuevoEstado,
            BigDecimal costoTotal
    ) throws Exception {

        ordenesServicioService.actualizarEstadoYCostoTransaccional(
                ordenId,
                nuevoEstado,
                costoTotal
        );
    }
}