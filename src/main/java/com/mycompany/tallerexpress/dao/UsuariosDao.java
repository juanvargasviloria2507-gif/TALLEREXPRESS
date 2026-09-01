/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.dao;

/**
 *
 * @author Coder
 */

import com.mycompany.tallerexpress.model.Usuarios;
import java.util.Optional;

public interface UsuariosDao {
    Usuarios create(Usuarios usuario) throws Exception;
    Optional<Usuarios> findByUsername(String username) throws Exception;
}
