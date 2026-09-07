/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.model.Usuarios;
import com.mycompany.tallerexpress.service.UsuariosService;
import com.mycompany.tallerexpress.service.impl.UsuariosServiceImpl;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author Coder
 */
public class UsuariosController {

    private final UsuariosService usuariosService = new UsuariosServiceImpl();

    public Usuarios login(String username, String password) throws Exception {
        return usuariosService.login(username, password);
    }

    public Usuarios registrarUsuario(Usuarios usuario) throws Exception {
        return usuariosService.create(usuario);
    }

    public List<Usuarios> listarUsuarios() throws Exception {
        return usuariosService.findAll();
    }

    public Optional<Usuarios> buscarUsuarioPorId(Integer id) throws Exception {
        return usuariosService.findById(id);
    }

    public Optional<Usuarios> buscarUsuarioPorUsername(String username) throws Exception {
        return usuariosService.findByUsername(username);
    }

    public void actualizarUsuario(Usuarios usuario) throws Exception {
        usuariosService.update(usuario);
    }

    public void eliminarUsuario(Integer id) throws Exception {
        usuariosService.delete(id);
    }
}