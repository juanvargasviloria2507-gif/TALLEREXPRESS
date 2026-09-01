/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.dao;

import com.mycompany.tallerexpress.model.OrdenesServicio;
import java.util.List;

public interface OrdenesServicioDao {
    OrdenesServicio registrarOrdenTransaccional(OrdenesServicio orden, Integer repuestoId, Integer cantidad) throws Exception;
    void actualizarEstadoYCostoTransaccional(Integer ordenId, String nuevoEstado, Double costoTotal) throws Exception;
    List<OrdenesServicio> findByVehiculo(Integer vehiculoId) throws Exception;
}
