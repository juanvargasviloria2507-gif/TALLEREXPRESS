/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.model.Cliente;
import com.mycompany.tallerexpress.service.ClienteService;
import com.mycompany.tallerexpress.service.impl.ClienteServiceImpl;

import java.util.List;

/**
 *
 * @author Coder
 */
public class ClienteController {

    private final ClienteService clienteService = new ClienteServiceImpl();

    public Cliente registrarCliente(Cliente cliente) throws Exception {
        return clienteService.create(cliente);
    }

    public List<Cliente> listarClientes() throws Exception {
        return clienteService.findAll();
    }

    public Cliente buscarClientePorId(Integer id) throws Exception {
        return clienteService.findById(id);
    }

    public void actualizarCliente(Cliente cliente) throws Exception {
        clienteService.update(cliente);
    }

    public void eliminarCliente(Integer id) throws Exception {
        clienteService.delete(id);
    }
}