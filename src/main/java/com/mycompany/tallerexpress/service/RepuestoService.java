/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.Repuesto;

import java.util.List;

public interface RepuestoService {

    Repuesto create(Repuesto repuesto) throws Exception;

    void update(Repuesto repuesto) throws Exception;

    List<Repuesto> findAll() throws Exception;

    Repuesto findById(Integer id) throws Exception;

    List<Repuesto> findByCategoriaOrProveedor(String filtro) throws Exception;

    void delete(Integer id) throws Exception;
}