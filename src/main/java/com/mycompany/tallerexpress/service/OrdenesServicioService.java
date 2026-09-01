/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.OrdenesServicio;
import java.util.List;

public interface OrdenesServicioService {
    OrdenesServicio registrarOrden(OrdenesServicio orden, Integer repuestoId, Integer cantidad) throws Exception;
    void actualizarEstadoYCosto(Integer ordenId, String nuevoEstado, Double costoTotal) throws Exception;
    List<OrdenesServicio> listarPorVehiculo(Integer vehiculoId) throws Exception;
}