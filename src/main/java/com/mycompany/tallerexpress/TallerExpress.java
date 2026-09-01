/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tallerexpress;

import com.mycompany.tallerexpress.controller.ClienteController;
import com.mycompany.tallerexpress.controller.OrdenesServicioController;
import com.mycompany.tallerexpress.controller.RepuestoController;
import com.mycompany.tallerexpress.controller.UsuariosController;
import com.mycompany.tallerexpress.model.Usuarios;

import javax.swing.JOptionPane;

public class TallerExpress {

    private static final UsuariosController usuariosController = new UsuariosController();
    private static final ClienteController clienteController = new ClienteController();
    private static final RepuestoController repuestoController = new RepuestoController();
    private static final OrdenesServicioController ordenesController = new OrdenesServicioController();

    private static Usuarios usuarioAutenticado = null;

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            if (usuarioAutenticado == null) {
                String[] opcionesAuth = {"Iniciar Sesión", "Salir"};
                int seleccion = JOptionPane.showOptionDialog(
                        null,
                        "¡Bienvenido a TallerExpress!\nPor favor, inicie sesión para continuar.",
                        "Autenticación - TallerExpress",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        opcionesAuth,
                        opcionesAuth[0]
                );

                if (seleccion == 0) {
                    usuarioAutenticado = usuariosController.login();
                } else {
                    salir = true;
                }
            } else {
                mostrarMenuPrincipal();
            }
        }

        JOptionPane.showMessageDialog(null, "¡Gracias por usar TallerExpress!", "Cierre de Sistema", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void mostrarMenuPrincipal() {
        String rol = usuarioAutenticado.getRole().toUpperCase();
        
        // Menú adaptado a los 4 controladores (Clientes incluye Vehículos)
        String[] options = {
            "Gestión de Repuestos",
            "Gestión de Clientes y Vehículos",
            "Órdenes de Servicio",
            "Gestión de Usuarios",
            "Cerrar Sesión",
            "Salir"
        };

        int choice = JOptionPane.showOptionDialog(
                null,
                "=== TALLEREXPRESS ===\nUsuario: " + usuarioAutenticado.getUsername() + " | Rol: [" + rol + "]\nSeleccione una opción:",
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == -1) {
            cerrarSesion();
            return;
        }

        try {
            switch (choice) {
                case 0 -> repuestoController.menuRepuestos();
                case 1 -> clienteController.iniciar();
                case 2 -> ordenesController.menuOrdenes();
                case 3 -> {
                    if ("ADMIN".equalsIgnoreCase(rol)) {
                        usuariosController.iniciar();
                    } else {
                        JOptionPane.showMessageDialog(null, "Acceso denegado. Se requieren permisos de ADMINISTRADOR.", "Permisos Insuficientes", JOptionPane.WARNING_MESSAGE);
                    }
                }
                case 4 -> cerrarSesion();
                case 5 -> System.exit(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error inesperado: " + e.getMessage(), "Error del Sistema", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void cerrarSesion() {
        usuarioAutenticado = null;
        JOptionPane.showMessageDialog(null, "Sesión cerrada correctamente.", "Cierre de Sesión", JOptionPane.INFORMATION_MESSAGE);
    }
}