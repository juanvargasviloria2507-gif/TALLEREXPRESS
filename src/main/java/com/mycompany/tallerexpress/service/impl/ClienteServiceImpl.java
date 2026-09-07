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

import java.util.List;

public class ClienteServiceImpl implements ClienteService {

    private final ClienteDao clienteDao = new ClientesImpl();

    @Override
    public Cliente create(Cliente cliente) throws Exception {

        if (cliente == null) {
            throw new BusinessException("El cliente es obligatorio.");
        }

        if (cliente.getTipoIdentificacion() == null
                || cliente.getTipoIdentificacion().isBlank()) {
            throw new BusinessException("El tipo de identificación es obligatorio.");
        }

        if (cliente.getNumeroIdentificacion() == null
                || cliente.getNumeroIdentificacion().isBlank()) {
            throw new BusinessException("El número de identificación es obligatorio.");
        }

        if (cliente.getNombreCompleto() == null
                || cliente.getNombreCompleto().isBlank()) {
            throw new BusinessException("El nombre completo es obligatorio.");
        }

        if (cliente.getEstado() == null || cliente.getEstado().isBlank()) {
            cliente.setEstado("ACTIVO");
        }

        if (cliente.getFechaRegistro() == null) {
            cliente.setFechaRegistro(
                    new java.sql.Date(System.currentTimeMillis())
            );
        }

        return clienteDao.create(cliente);
    }

    @Override
    public List<Cliente> findAll() throws Exception {
        return clienteDao.findAll();
    }

    @Override
    public Cliente findById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new BusinessException("El ID del cliente no es válido.");
        }

        return clienteDao.findById(id);
    }

    @Override
    public void update(Cliente cliente) throws Exception {

        if (cliente == null) {
            throw new BusinessException("El cliente es obligatorio.");
        }

        if (cliente.getId() == null || cliente.getId() <= 0) {
            throw new BusinessException("El ID del cliente no es válido.");
        }

        if (cliente.getTipoIdentificacion() == null
                || cliente.getTipoIdentificacion().isBlank()) {
            throw new BusinessException("El tipo de identificación es obligatorio.");
        }

        if (cliente.getNumeroIdentificacion() == null
                || cliente.getNumeroIdentificacion().isBlank()) {
            throw new BusinessException("El número de identificación es obligatorio.");
        }

        if (cliente.getNombreCompleto() == null
                || cliente.getNombreCompleto().isBlank()) {
            throw new BusinessException("El nombre completo es obligatorio.");
        }

        clienteDao.update(cliente);
    }

    @Override
    public void delete(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new BusinessException("El ID del cliente no es válido.");
        }

        clienteDao.delete(id);
    }
}
