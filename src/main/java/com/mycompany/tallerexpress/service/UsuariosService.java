/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.Usuarios;

import java.util.List;
import java.util.Optional;

public interface UsuariosService {

    Usuarios create(Usuarios usuario) throws Exception;

    Usuarios login(String username, String password) throws Exception;

    List<Usuarios> findAll() throws Exception;

    Optional<Usuarios> findById(Integer id) throws Exception;

    Optional<Usuarios> findByUsername(String username) throws Exception;

    void update(Usuarios usuario) throws Exception;

    void delete(Integer id) throws Exception;
}