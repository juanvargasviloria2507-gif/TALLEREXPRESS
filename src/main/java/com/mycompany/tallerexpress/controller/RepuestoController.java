package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.model.Repuesto;
import com.mycompany.tallerexpress.service.RepuestoService;
import com.mycompany.tallerexpress.service.impl.RepuestoServiceImpl;

import java.util.List;

public class RepuestoController {

    private final RepuestoService repuestoService = new RepuestoServiceImpl();

    public Repuesto registrarRepuesto(Repuesto repuesto) throws Exception {
        return repuestoService.create(repuesto);
    }

    public List<Repuesto> listarRepuestos() throws Exception {
        return repuestoService.findAll();
    }

    public List<Repuesto> filtrarPorCategoriaOProveedor(String filtro) throws Exception {
        return repuestoService.findByCategoriaOrProveedor(filtro);
    }

    public Repuesto buscarRepuestoPorId(Integer id) throws Exception {
        return repuestoService.findById(id);
    }

    public void actualizarRepuesto(Repuesto repuesto) throws Exception {
        repuestoService.update(repuesto);
    }

    public void eliminarRepuesto(Integer id) throws Exception {
        repuestoService.delete(id);
    }
}