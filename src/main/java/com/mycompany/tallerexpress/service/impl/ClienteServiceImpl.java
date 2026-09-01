/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service.impl;

import com.mycompany.tallerexpress.dao.ClienteDao;
import com.mycompany.tallerexpress.dao.impl.ClientesImpl;
import com.mycompany.tallerexpress.exception.BusinessException;
import com.mycompany.tallerexpress.model.Cliente;
import com.mycompany.tallerexpress.service.ClienteService;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteDao clienteDao = new ClientesImpl();

    @Override
    public Cliente create(Cliente cliente) throws Exception {
        if (cliente.getDocumento() == null || cliente.getDocumento().isBlank()) {
            throw new BusinessException("El documento es obligatorio.");
        }
        if (cliente.getNombre() == null || cliente.getNombre().isBlank()) {
            throw new BusinessException("El nombre es obligatorio.");
        }

        if (cliente.getIsActivo() == null) {
            cliente.setIsActivo(true);
        }
        if (cliente.getCreatedAt() == null) {
            cliente.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        }

        return clienteDao.create(cliente);
    }

    @Override
    public List<Cliente> findAll() throws Exception {
        return clienteDao.findAll();
    }

    @Override
    public Optional<Cliente> buscarPorDocumento(String documento) throws Exception {
        if (documento == null || documento.isBlank()) {
            throw new BusinessException("El documento de búsqueda no puede estar vacío.");
        }
        return clienteDao.findAll().stream()
                .filter(c -> documento.equalsIgnoreCase(c.getDocumento()))
                .findFirst();
    }
}
