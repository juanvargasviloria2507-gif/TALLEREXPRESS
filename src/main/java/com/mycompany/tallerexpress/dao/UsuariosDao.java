package com.mycompany.tallerexpress.dao;

import com.mycompany.tallerexpress.model.Usuarios;
import java.util.List;
import java.util.Optional;

public interface UsuariosDao {

    Usuarios create(Usuarios usuario) throws Exception;

    List<Usuarios> findAll() throws Exception;

    Optional<Usuarios> findById(Integer id) throws Exception;

    Optional<Usuarios> findByUsername(String username) throws Exception;

    void update(Usuarios usuario) throws Exception;

    void delete(Integer id) throws Exception;
}