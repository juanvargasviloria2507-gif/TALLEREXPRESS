/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.model.Usuarios;
import com.mycompany.tallerexpress.service.UsuariosService;
import com.mycompany.tallerexpress.service.impl.UsuariosServiceImpl;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class UsuariosController {

    private final UsuariosService usuariosService = new UsuariosServiceImpl();

    public Usuarios login() {
        try {
            String username = JOptionPane.showInputDialog(null, "Nombre de Usuario:");
            if (username == null || username.isBlank()) return null;

            JPasswordField pf = new JPasswordField();
            int option = JOptionPane.showConfirmDialog(
                    null, 
                    pf, 
                    "Contraseña:", 
                    JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.PLAIN_MESSAGE
            );

            if (option != JOptionPane.OK_OPTION) return null;

            String password = new String(pf.getPassword());
            if (password.isBlank()) return null;

            // Se cambia authenticate por login
            Usuarios user = usuariosService.login(username, password);
            JOptionPane.showMessageDialog(null, "¡Bienvenido, " + user.getUsername() + "!");
            return user;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error Autenticación", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    public void iniciar() {
        String[] opciones = {"Registrar Usuario", "Volver"};
        int sel = JOptionPane.showOptionDialog(
                null, 
                "Gestión de Usuarios", 
                "Usuarios",
                JOptionPane.DEFAULT_OPTION, 
                JOptionPane.PLAIN_MESSAGE, 
                null, 
                opciones, 
                opciones[0]
        );

        if (sel == 0) {
            try {
                String user = JOptionPane.showInputDialog("Nuevo Username:");
                if (user == null || user.isBlank()) return;

                String pass = JOptionPane.showInputDialog("Contraseña:");
                if (pass == null || pass.isBlank()) return;

                String rol = JOptionPane.showInputDialog("Rol (ADMIN / RECEPCIONISTA):");

                Usuarios u = new Usuarios();
                u.setUsername(user);
                u.setPassword(pass);
                u.setRole(rol);

                usuariosService.create(u);
                JOptionPane.showMessageDialog(null, "Usuario creado correctamente.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}