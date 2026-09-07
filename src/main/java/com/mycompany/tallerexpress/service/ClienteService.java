/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.Cliente;

import java.util.List;

public interface ClienteService {

    Cliente create(Cliente cliente) throws Exception;

    List<Cliente> findAll() throws Exception;

    Cliente findById(Integer id) throws Exception;

    void update(Cliente cliente) throws Exception;

    void delete(Integer id) throws Exception;
}