/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.dao;

import com.mycompany.tallerexpress.model.Repuesto;
import java.util.List;

public interface RepuestoDao {
    Repuesto create(Repuesto r) throws Exception;
    void update(Repuesto r) throws Exception;
    List<Repuesto> findAll() throws Exception;
    List<Repuesto> findByCategoriaOrProveedor(String filtro) throws Exception;
}
