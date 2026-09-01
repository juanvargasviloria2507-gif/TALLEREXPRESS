/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.service;

import com.mycompany.tallerexpress.model.Usuarios;

public interface UsuariosService {
    Usuarios create(Usuarios usuario) throws Exception;
    Usuarios login(String username, String password) throws Exception;
}