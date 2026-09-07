/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.Vehiculos;

import java.util.List;
import java.util.Optional;

public interface VehiculosService {

    Vehiculos create(Vehiculos vehiculo) throws Exception;

    Optional<Vehiculos> findById(Integer id) throws Exception;

    Optional<Vehiculos> findByPlaca(String placa) throws Exception;

    List<Vehiculos> findByClienteId(Integer clienteId) throws Exception;

    List<Vehiculos> findAll() throws Exception;

    void update(Vehiculos vehiculo) throws Exception;

    void delete(Integer id) throws Exception;
}