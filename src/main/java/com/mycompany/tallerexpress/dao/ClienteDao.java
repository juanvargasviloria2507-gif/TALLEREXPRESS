package com.mycompany.tallerexpress.dao;

import com.mycompany.tallerexpress.model.Cliente;
import java.util.List;

public interface ClienteDao {

    Cliente create(Cliente cliente) throws Exception;

    List<Cliente> findAll() throws Exception;

    Cliente findById(Integer id) throws Exception;

    void update(Cliente cliente) throws Exception;

    void delete(Integer id) throws Exception;
}