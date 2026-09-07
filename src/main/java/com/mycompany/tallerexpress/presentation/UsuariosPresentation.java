package com.mycompany.tallerexpress.presentation;

import com.mycompany.tallerexpress.controller.UsuariosController;
import com.mycompany.tallerexpress.model.Usuarios;
import com.mycompany.tallerexpress.util.TableFormatter;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.Optional;

public class UsuariosPresentation {

    private final UsuariosController usuariosController =
            new UsuariosController();

    public void menu() {

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    null,
                    """
                    === GESTIÓN DE USUARIOS ===

                    1. Registrar usuario
                    2. Listar usuarios
                    3. Buscar por ID
                    4. Buscar por username
                    5. Actualizar usuario
                    6. Eliminar usuario
                    0. Volver
                    """,
                    "Usuarios",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == null) {
                return;
            }

            try {
                switch (opcion) {
                    case "1" -> registrar();
                    case "2" -> listar();
                    case "3" -> buscarPorId();
                    case "4" -> buscarPorUsername();
                    case "5" -> actualizar();
                    case "6" -> eliminar();
                    case "0" -> {
                    }
                    default -> JOptionPane.showMessageDialog(
                            null,
                            "Opción no válida."
                    );
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(
                        null,
                        e.getMessage(),
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } while (!opcion.equals("0"));
    }

    public Usuarios iniciarSesion() throws Exception {

        String username = JOptionPane.showInputDialog(
                "Nombre de usuario:"
        );

        String password = JOptionPane.showInputDialog(
                "Contraseña:"
        );

        return usuariosController.login(username, password);
    }

    private void registrar() throws Exception {

        Usuarios usuario = new Usuarios();

        usuario.setUsername(
                JOptionPane.showInputDialog("Nombre de usuario:")
        );

        usuario.setPassword(
                JOptionPane.showInputDialog("Contraseña:")
        );

        usuario.setRole(
                JOptionPane.showInputDialog(
                        "Rol (ADMIN/RECEPCIONISTA):"
                )
        );

        usuario.setEstado(
                JOptionPane.showInputDialog(
                        "Estado (ACTIVO/INACTIVO):"
                )
        );

        Usuarios creado =
                usuariosController.registrarUsuario(usuario);

        JOptionPane.showMessageDialog(
                null,
                "Usuario registrado correctamente.\nID: "
                + creado.getId()
        );
    }

    private void listar() throws Exception {

        List<Usuarios> usuarios =
                usuariosController.listarUsuarios();

        String[] headers = {
            "ID",
            "Username",
            "Rol",
            "Estado"
        };

        String tabla = TableFormatter.formatTable(
                "Lista de usuarios",
                headers,
                usuarios,
                usuario -> new String[]{
                    String.valueOf(usuario.getId()),
                    usuario.getUsername(),
                    usuario.getRole(),
                    usuario.getEstado()
                }
        );

        JOptionPane.showMessageDialog(
                null,
                tabla,
                "Usuarios",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void buscarPorId() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del usuario:")
        );

        Optional<Usuarios> resultado =
                usuariosController.buscarUsuarioPorId(id);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Usuario no encontrado."
            );
            return;
        }

        mostrarUsuario(resultado.get());
    }

    private void buscarPorUsername() throws Exception {

        String username = JOptionPane.showInputDialog(
                "Nombre de usuario:"
        );

        Optional<Usuarios> resultado =
                usuariosController.buscarUsuarioPorUsername(username);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Usuario no encontrado."
            );
            return;
        }

        mostrarUsuario(resultado.get());
    }

    private void actualizar() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del usuario:")
        );

        Optional<Usuarios> resultado =
                usuariosController.buscarUsuarioPorId(id);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Usuario no encontrado."
            );
            return;
        }

        Usuarios usuario = resultado.get();

        usuario.setUsername(
                JOptionPane.showInputDialog(
                        "Username:",
                        usuario.getUsername()
                )
        );

        usuario.setPassword(
                JOptionPane.showInputDialog(
                        "Contraseña:",
                        usuario.getPassword()
                )
        );

        usuario.setRole(
                JOptionPane.showInputDialog(
                        "Rol:",
                        usuario.getRole()
                )
        );

        usuario.setEstado(
                JOptionPane.showInputDialog(
                        "Estado:",
                        usuario.getEstado()
                )
        );

        usuariosController.actualizarUsuario(usuario);

        JOptionPane.showMessageDialog(
                null,
                "Usuario actualizado correctamente."
        );
    }

    private void eliminar() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del usuario:")
        );

        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Desea eliminar el usuario con ID " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            usuariosController.eliminarUsuario(id);

            JOptionPane.showMessageDialog(
                    null,
                    "Usuario eliminado correctamente."
            );
        }
    }

    private void mostrarUsuario(Usuarios usuario) {

        JOptionPane.showMessageDialog(
                null,
                "ID: " + usuario.getId()
                + "\nUsername: " + usuario.getUsername()
                + "\nRol: " + usuario.getRole()
                + "\nEstado: " + usuario.getEstado()
        );
    }
}