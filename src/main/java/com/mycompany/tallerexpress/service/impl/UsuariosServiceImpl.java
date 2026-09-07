package com.mycompany.tallerexpress.service.impl;

import com.mycompany.tallerexpress.dao.UsuariosDao;
import com.mycompany.tallerexpress.dao.impl.UsuariosImpl;
import com.mycompany.tallerexpress.exception.BusinessException;
import com.mycompany.tallerexpress.model.Usuarios;
import com.mycompany.tallerexpress.service.UsuariosService;
import com.mycompany.tallerexpress.util.HttpLogger;

import java.util.List;
import java.util.Optional;

public class UsuariosServiceImpl implements UsuariosService {

    private final UsuariosDao usuariosDao = new UsuariosImpl();

    @Override
    public Usuarios create(Usuarios usuario) throws Exception {

        HttpLogger.log(
                "POST",
                "/api/usuarios",
                201,
                "Registrando nuevo usuario"
        );

        if (usuario == null) {
            throw new BusinessException("El usuario es obligatorio.");
        }

        if (usuario.getUsername() == null
                || usuario.getUsername().isBlank()) {
            throw new BusinessException("El nombre de usuario es obligatorio.");
        }

        if (usuario.getPassword() == null
                || usuario.getPassword().isBlank()) {
            throw new BusinessException("La contraseña es obligatoria.");
        }

        if (usuario.getRole() == null
                || usuario.getRole().isBlank()) {
            usuario.setRole("RECEPCIONISTA");
        }

        if (usuario.getEstado() == null
                || usuario.getEstado().isBlank()) {
            usuario.setEstado("ACTIVO");
        }

        return usuariosDao.create(usuario);
    }

    @Override
    public Usuarios login(String username, String password) throws Exception {

        HttpLogger.log(
                "POST",
                "/api/auth/login",
                200,
                "Intento de inicio de sesión"
        );

        if (username == null || username.isBlank()) {
            throw new BusinessException("El nombre de usuario es obligatorio.");
        }

        if (password == null || password.isBlank()) {
            throw new BusinessException("La contraseña es obligatoria.");
        }

        Optional<Usuarios> opt = usuariosDao.findByUsername(username);

        if (opt.isEmpty()) {
            throw new BusinessException("Credenciales inválidas.");
        }

        Usuarios user = opt.get();

        if (!user.getPassword().equals(password)) {
            throw new BusinessException("Credenciales inválidas.");
        }

        if (!"ACTIVO".equalsIgnoreCase(user.getEstado())) {
            throw new BusinessException("El usuario se encuentra inactivo.");
        }

        return user;
    }

    @Override
    public List<Usuarios> findAll() throws Exception {

        HttpLogger.log(
                "GET",
                "/api/usuarios",
                200,
                "Listando usuarios"
        );

        return usuariosDao.findAll();
    }

    @Override
    public Optional<Usuarios> findById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new BusinessException("El ID del usuario no es válido.");
        }

        HttpLogger.log(
                "GET",
                "/api/usuarios/" + id,
                200,
                "Consultando usuario"
        );

        return usuariosDao.findById(id);
    }

    @Override
    public Optional<Usuarios> findByUsername(String username) throws Exception {

        if (username == null || username.isBlank()) {
            throw new BusinessException(
                    "El nombre de usuario es obligatorio."
            );
        }

        HttpLogger.log(
                "GET",
                "/api/usuarios/username/" + username,
                200,
                "Consultando usuario por nombre"
        );

        return usuariosDao.findByUsername(username);
    }

    @Override
    public void update(Usuarios usuario) throws Exception {

        if (usuario == null) {
            throw new BusinessException("El usuario es obligatorio.");
        }

        if (usuario.getId() == null || usuario.getId() <= 0) {
            throw new BusinessException("El ID del usuario no es válido.");
        }

        if (usuario.getUsername() == null
                || usuario.getUsername().isBlank()) {
            throw new BusinessException("El nombre de usuario es obligatorio.");
        }

        if (usuario.getPassword() == null
                || usuario.getPassword().isBlank()) {
            throw new BusinessException("La contraseña es obligatoria.");
        }

        if (usuario.getRole() == null
                || usuario.getRole().isBlank()) {
            throw new BusinessException("El rol del usuario es obligatorio.");
        }

        if (usuario.getEstado() == null
                || usuario.getEstado().isBlank()) {
            throw new BusinessException("El estado del usuario es obligatorio.");
        }

        HttpLogger.log(
                "PUT",
                "/api/usuarios/" + usuario.getId(),
                200,
                "Actualizando usuario"
        );

        usuariosDao.update(usuario);
    }

    @Override
    public void delete(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new BusinessException("El ID del usuario no es válido.");
        }

        HttpLogger.log(
                "DELETE",
                "/api/usuarios/" + id,
                200,
                "Eliminando usuario"
        );

        usuariosDao.delete(id);
    }
}