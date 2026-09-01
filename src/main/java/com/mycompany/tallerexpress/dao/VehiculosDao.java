/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.dao;

/**
 *
 * @author Coder
 */

import com.mycompany.tallerexpress.model.Vehiculos;
import java.util.List;
import java.util.Optional;

public interface VehiculosDao {
    Vehiculos create(Vehiculos vehiculo) throws Exception;
    Optional<Vehiculos> findByPlaca(String placa) throws Exception;
    List<Vehiculos> findByClienteId(Integer clienteId) throws Exception;
}
