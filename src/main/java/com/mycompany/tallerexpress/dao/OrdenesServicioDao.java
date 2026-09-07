package com.mycompany.tallerexpress.dao;

import com.mycompany.tallerexpress.model.OrdenesServicio;
import java.math.BigDecimal;
import java.util.List;

public interface OrdenesServicioDao {

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

    List<OrdenesServicio> findByVehiculo(
            Integer vehiculoId
    ) throws Exception;
}