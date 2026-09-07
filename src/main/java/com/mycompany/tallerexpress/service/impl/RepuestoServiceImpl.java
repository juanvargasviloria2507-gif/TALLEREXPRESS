package com.mycompany.tallerexpress.service.impl;

import com.mycompany.tallerexpress.dao.RepuestoDao;
import com.mycompany.tallerexpress.dao.impl.RepuestoImpl;
import com.mycompany.tallerexpress.exception.BusinessException;
import com.mycompany.tallerexpress.model.Repuesto;
import com.mycompany.tallerexpress.service.RepuestoService;
import com.mycompany.tallerexpress.util.HttpLogger;

import java.util.List;

public class RepuestoServiceImpl implements RepuestoService {

    private final RepuestoDao repuestoDao = new RepuestoImpl();

    @Override
    public Repuesto create(Repuesto repuesto) throws Exception {

        HttpLogger.log(
                "POST",
                "/api/repuestos",
                201,
                "Registrando repuesto"
        );

        if (repuesto == null) {
            throw new BusinessException(
                    "El repuesto es obligatorio."
            );
        }

        if (repuesto.getCodigoReferencia() == null
                || repuesto.getCodigoReferencia().isBlank()) {

            throw new BusinessException(
                    "El código de referencia es obligatorio."
            );
        }

        if (repuesto.getNombre() == null
                || repuesto.getNombre().isBlank()) {

            throw new BusinessException(
                    "El nombre del repuesto es obligatorio."
            );
        }

        if (repuesto.getStockTotal() < 0) {
            throw new BusinessException(
                    "El stock total no puede ser negativo."
            );
        }

        if (repuesto.getStockDisponible() < 0) {
            throw new BusinessException(
                    "El stock disponible no puede ser negativo."
            );
        }

        if (repuesto.getStockDisponible()
                > repuesto.getStockTotal()) {

            throw new BusinessException(
                    "El stock disponible no puede ser mayor "
                    + "que el stock total."
            );
        }

        if (repuesto.getPrecioUnitario() < 0) {
            throw new BusinessException(
                    "El precio unitario no puede ser negativo."
            );
        }

        if (repuesto.getEstado() == null
                || repuesto.getEstado().isBlank()) {

            repuesto.setEstado("ACTIVO");
        }

        // La base de datos exige que fecha_registro
        // no sea NULL.
        if (repuesto.getFechaRegistro() == null) {

            repuesto.setFechaRegistro(
                    new java.sql.Date(
                            System.currentTimeMillis()
                    )
            );
        }

        return repuestoDao.create(repuesto);
    }

    @Override
    public void update(Repuesto repuesto) throws Exception {

        if (repuesto == null) {
            throw new BusinessException(
                    "El repuesto es obligatorio."
            );
        }

        if (repuesto.getId() == null
                || repuesto.getId() <= 0) {

            throw new BusinessException(
                    "El ID del repuesto no es válido."
            );
        }

        if (repuesto.getCodigoReferencia() == null
                || repuesto.getCodigoReferencia().isBlank()) {

            throw new BusinessException(
                    "El código de referencia es obligatorio."
            );
        }

        if (repuesto.getNombre() == null
                || repuesto.getNombre().isBlank()) {

            throw new BusinessException(
                    "El nombre del repuesto es obligatorio."
            );
        }

        if (repuesto.getStockTotal() < 0) {
            throw new BusinessException(
                    "El stock total no puede ser negativo."
            );
        }

        if (repuesto.getStockDisponible() < 0) {
            throw new BusinessException(
                    "El stock disponible no puede ser negativo."
            );
        }

        if (repuesto.getStockDisponible()
                > repuesto.getStockTotal()) {

            throw new BusinessException(
                    "El stock disponible no puede ser mayor "
                    + "que el stock total."
            );
        }

        if (repuesto.getPrecioUnitario() < 0) {
            throw new BusinessException(
                    "El precio unitario no puede ser negativo."
            );
        }

        if (repuesto.getEstado() == null
                || repuesto.getEstado().isBlank()) {

            throw new BusinessException(
                    "El estado del repuesto es obligatorio."
            );
        }

        HttpLogger.log(
                "PUT",
                "/api/repuestos/" + repuesto.getId(),
                200,
                "Actualizando repuesto"
        );

        repuestoDao.update(repuesto);
    }

    @Override
    public List<Repuesto> findAll() throws Exception {

        HttpLogger.log(
                "GET",
                "/api/repuestos",
                200,
                "Listando repuestos"
        );

        return repuestoDao.findAll();
    }

    @Override
    public Repuesto findById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new BusinessException(
                    "El ID del repuesto no es válido."
            );
        }

        HttpLogger.log(
                "GET",
                "/api/repuestos/" + id,
                200,
                "Consultando repuesto"
        );

        return repuestoDao.findById(id);
    }

    @Override
    public List<Repuesto> findByCategoriaOrProveedor(
            String filtro
    ) throws Exception {

        if (filtro == null) {
            filtro = "";
        }

        HttpLogger.log(
                "GET",
                "/api/repuestos/filtro",
                200,
                "Filtrando repuestos"
        );

        return repuestoDao.findByCategoriaOrProveedor(filtro);
    }

    @Override
    public void delete(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new BusinessException(
                    "El ID del repuesto no es válido."
            );
        }

        HttpLogger.log(
                "DELETE",
                "/api/repuestos/" + id,
                200,
                "Eliminando repuesto"
        );

        repuestoDao.delete(id);
    }
}