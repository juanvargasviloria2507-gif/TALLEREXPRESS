/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.Repuesto;
import java.util.List;

public interface RepuestoService {
    Repuesto registrar(Repuesto repuesto) throws Exception;
    void actualizar(Repuesto repuesto) throws Exception;
    List<Repuesto> listarTodos() throws Exception;
    List<Repuesto> filtrarPorCategoriaOProveedor(String filtro) throws Exception;
}