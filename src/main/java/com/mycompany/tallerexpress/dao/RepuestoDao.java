package com.mycompany.tallerexpress.dao;

import com.mycompany.tallerexpress.model.Repuesto;
import java.util.List;

public interface RepuestoDao {

    Repuesto create(Repuesto r) throws Exception;

    void update(Repuesto r) throws Exception;

    List<Repuesto> findAll() throws Exception;

    Repuesto findById(Integer id) throws Exception;

    List<Repuesto> findByCategoriaOrProveedor(String filtro) throws Exception;

    void delete(Integer id) throws Exception;
}