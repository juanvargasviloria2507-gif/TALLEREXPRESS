package com.mycompany.tallerexpress;

import com.mycompany.tallerexpress.presentation.ClientePresentation;
import com.mycompany.tallerexpress.presentation.OrdenesServicioPresentation;
import com.mycompany.tallerexpress.presentation.RepuestoPresentation;
import com.mycompany.tallerexpress.presentation.UsuariosPresentation;
import com.mycompany.tallerexpress.presentation.VehiculosPresentation;
import com.mycompany.tallerexpress.model.Usuarios;

import javax.swing.JOptionPane;

public class TallerExpress {

    private static final ClientePresentation clientePresentation =
            new ClientePresentation();

    private static final VehiculosPresentation vehiculosPresentation =
            new VehiculosPresentation();

    private static final RepuestoPresentation repuestoPresentation =
            new RepuestoPresentation();

    private static final OrdenesServicioPresentation ordenesPresentation =
            new OrdenesServicioPresentation();

    private static final UsuariosPresentation usuariosPresentation =
            new UsuariosPresentation();

    private static Usuarios usuarioAutenticado = null;

    public static void main(String[] args) {

        boolean salir = false;

        while (!salir) {

            if (usuarioAutenticado == null) {

                String[] opcionesAuth = {
                    "Iniciar Sesión",
                    "Salir"
                };

                int seleccion = JOptionPane.showOptionDialog(
                        null,
                        "¡Bienvenido a TallerExpress!\n"
                        + "Por favor, inicie sesión para continuar.",
                        "Autenticación - TallerExpress",
                        JOptionPane.DEFAULT_OPTION,
                        JOptionPane.INFORMATION_MESSAGE,
                        null,
                        opcionesAuth,
                        opcionesAuth[0]
                );

                if (seleccion == 0) {

                    try {
                        usuarioAutenticado =
                                usuariosPresentation.iniciarSesion();

                        JOptionPane.showMessageDialog(
                                null,
                                "Inicio de sesión exitoso.",
                                "Bienvenido",
                                JOptionPane.INFORMATION_MESSAGE
                        );

                    } catch (Exception e) {

                        JOptionPane.showMessageDialog(
                                null,
                                e.getMessage(),
                                "Error de autenticación",
                                JOptionPane.ERROR_MESSAGE
                        );
                    }

                } else {

                    salir = true;
                }

            } else {

                mostrarMenuPrincipal();
            }
        }

        JOptionPane.showMessageDialog(
                null,
                "¡Gracias por usar TallerExpress!",
                "Cierre de Sistema",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private static void mostrarMenuPrincipal() {

        String rol = usuarioAutenticado
                .getRole()
                .toUpperCase();

        String[] opciones = {
            "Gestión de Clientes",
            "Gestión de Vehículos",
            "Gestión de Repuestos",
            "Órdenes de Servicio",
            "Gestión de Usuarios",
            "Cerrar Sesión",
            "Salir"
        };

        int seleccion = JOptionPane.showOptionDialog(
                null,
                "=== TALLEREXPRESS ===\n"
                + "Usuario: " + usuarioAutenticado.getUsername()
                + " | Rol: [" + rol + "]\n\n"
                + "Seleccione una opción:",
                "Menú Principal",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (seleccion == -1) {
            cerrarSesion();
            return;
        }

        try {

            switch (seleccion) {

                case 0 -> clientePresentation.menu();

                case 1 -> vehiculosPresentation.menu();

                case 2 -> repuestoPresentation.menu();

                case 3 -> ordenesPresentation.menu();

                case 4 -> {

                    if ("ADMIN".equalsIgnoreCase(rol)) {

                        usuariosPresentation.menu();

                    } else {

                        JOptionPane.showMessageDialog(
                                null,
                                "Acceso denegado. "
                                + "Se requieren permisos de ADMINISTRADOR.",
                                "Permisos Insuficientes",
                                JOptionPane.WARNING_MESSAGE
                        );
                    }
                }

                case 5 -> cerrarSesion();

                case 6 -> System.exit(0);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Ocurrió un error inesperado: "
                    + e.getMessage(),
                    "Error del Sistema",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private static void cerrarSesion() {

        usuarioAutenticado = null;

        JOptionPane.showMessageDialog(
                null,
                "Sesión cerrada correctamente.",
                "Cierre de Sesión",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}