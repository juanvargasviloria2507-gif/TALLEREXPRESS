/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.Vehiculos;
import java.util.List;

public interface VehiculosService {
    Vehiculos registrar(Vehiculos vehiculo) throws Exception;
    List<Vehiculos> listarPorCliente(Integer clienteId) throws Exception;
}
