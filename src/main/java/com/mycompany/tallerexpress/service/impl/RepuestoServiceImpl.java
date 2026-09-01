/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
    public Repuesto registrar(Repuesto repuesto) throws Exception {
        HttpLogger.log("POST", "/api/repuestos", 201, "Creando nuevo repuesto");

        if (repuesto.getCodigoReferencia() == null || repuesto.getCodigoReferencia().isBlank()) {
            throw new BusinessException("El código de referencia es obligatorio.");
        }
        if (repuesto.getStockTotal() < 0) {
            throw new BusinessException("El stock no puede ser negativo.");
        }

        return repuestoDao.create(repuesto);
    }

    @Override
    public void actualizar(Repuesto repuesto) throws Exception {
        HttpLogger.log("PUT", "/api/repuestos/" + repuesto.getId(), 200, "Actualizando repuesto");
        repuestoDao.update(repuesto);
    }

    @Override
    public List<Repuesto> listarTodos() throws Exception {
        HttpLogger.log("GET", "/api/repuestos", 200, "Listando repuestos");
        return repuestoDao.findAll();
    }

    @Override
    public List<Repuesto> filtrarPorCategoriaOProveedor(String filtro) throws Exception {
        HttpLogger.log("GET", "/api/repuestos/filtrar?query=" + filtro, 200, "Filtrando repuestos");
        return repuestoDao.findByCategoriaOrProveedor(filtro);
    }
}