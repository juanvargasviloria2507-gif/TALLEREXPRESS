/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.dao;

import com.mycompany.tallerexpress.model.Cliente;
import java.util.List;

public interface ClienteDao {
    Cliente create(Cliente cliente) throws Exception;
    List<Cliente> findAll() throws Exception;
}
