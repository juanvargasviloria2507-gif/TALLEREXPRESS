/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.Cliente;
import java.util.List;
import java.util.Optional;

public interface ClienteService {
    Cliente create(Cliente cliente) throws Exception;
    List<Cliente> findAll() throws Exception;
    Optional<Cliente> buscarPorDocumento(String documento) throws Exception;
}
