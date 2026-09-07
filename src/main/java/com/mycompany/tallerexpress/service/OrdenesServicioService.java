/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.OrdenesServicio;

import java.math.BigDecimal;
import java.util.List;

public interface OrdenesServicioService {

    OrdenesServicio registrarOrdenTransaccional(
            OrdenesServicio orden,
            Integer repuestoId,
            Integer cantidad
    ) throws Exception;

    void actualizarEstadoYCostoTransaccional(
            Integer ordenId,
            String nuevoEstado,
            BigDecimal costoTotal
    ) throws Exception;

    List<OrdenesServicio> findByVehiculo(Integer vehiculoId) throws Exception;
}