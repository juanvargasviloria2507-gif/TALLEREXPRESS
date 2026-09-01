/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service.impl;

import com.mycompany.tallerexpress.dao.UsuariosDao;
import com.mycompany.tallerexpress.dao.impl.UsuariosImpl;
import com.mycompany.tallerexpress.exception.BusinessException;
import com.mycompany.tallerexpress.model.Usuarios;
import com.mycompany.tallerexpress.service.UsuariosService;
import com.mycompany.tallerexpress.util.HttpLogger;

import java.util.Optional;

public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosDao usuariosDao = new UsuariosImpl();

    @Override
    public Usuarios create(Usuarios usuario) throws Exception {
        HttpLogger.log("POST", "/api/usuarios", 201, "Registrando nuevo usuario");

        if (usuario.getUsername() == null || usuario.getUsername().isBlank()) {
            throw new BusinessException("El nombre de usuario es obligatorio.");
        }
        if (usuario.getPassword() == null || usuario.getPassword().isBlank()) {
            throw new BusinessException("La contraseña es obligatoria.");
        }

        // Decorador: Aplica propiedades por defecto requeridas por la prueba
        if (usuario.getRole() == null || usuario.getRole().isBlank()) {
            usuario.setRole("RECEPCIONISTA");
        }
        if (usuario.getEstado() == null || usuario.getEstado().isBlank()) {
            usuario.setEstado("ACTIVO");
        }

        return usuariosDao.create(usuario);
    }

    @Override
    public Usuarios login(String username, String password) throws Exception {
        HttpLogger.log("POST", "/api/auth/login", 200, "Intento de inicio de sesión");

        Optional<Usuarios> opt = usuariosDao.findByUsername(username);
        if (opt.isEmpty()) {
            throw new BusinessException("Credenciales inválidas: usuario no encontrado.");
        }

        Usuarios user = opt.get();
        if (!user.getPassword().equals(password)) {
            throw new BusinessException("Credenciales inválidas: contraseña incorrecta.");
        }
        if (!"ACTIVO".equalsIgnoreCase(user.getEstado())) {
            throw new BusinessException("El usuario se encuentra inactivo.");
        }

        return user;
    }
}
