package com.mycompany.tallerexpress.presentation;

import com.mycompany.tallerexpress.controller.ClienteController;
import com.mycompany.tallerexpress.model.Cliente;
import com.mycompany.tallerexpress.util.TableFormatter;

import javax.swing.JOptionPane;
import java.util.List;

public class ClientePresentation {

    private final ClienteController clienteController = new ClienteController();

    public void menu() {

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    null,
                    """
                    === GESTIÓN DE CLIENTES ===

                    1. Registrar cliente
                    2. Listar clientes
                    3. Buscar cliente por ID
                    4. Actualizar cliente
                    5. Eliminar cliente
                    0. Volver
                    """,
                    "Clientes",
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
                    case "4" -> actualizar();
                    case "5" -> eliminar();
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

    private void registrar() throws Exception {

        Cliente cliente = new Cliente();

        cliente.setTipoIdentificacion(
                JOptionPane.showInputDialog("Tipo de identificación:")
        );

        cliente.setNumeroIdentificacion(
                JOptionPane.showInputDialog("Número de identificación:")
        );

        cliente.setNombreCompleto(
                JOptionPane.showInputDialog("Nombre completo:")
        );

        cliente.setTelefono(
                JOptionPane.showInputDialog("Teléfono:")
        );

        cliente.setCorreo(
                JOptionPane.showInputDialog("Correo:")
        );

        cliente.setDireccion(
                JOptionPane.showInputDialog("Dirección:")
        );

        Cliente creado = clienteController.registrarCliente(cliente);

        JOptionPane.showMessageDialog(
                null,
                "Cliente registrado correctamente.\nID: " + creado.getId()
        );
    }

    private void listar() throws Exception {

        List<Cliente> clientes = clienteController.listarClientes();

        String[] headers = {
            "ID",
            "Tipo ID",
            "Identificación",
            "Nombre",
            "Teléfono",
            "Correo",
            "Estado"
        };

        String tabla = TableFormatter.formatTable(
                "Lista de clientes",
                headers,
                clientes,
                cliente -> new String[]{
                    String.valueOf(cliente.getId()),
                    cliente.getTipoIdentificacion(),
                    cliente.getNumeroIdentificacion(),
                    cliente.getNombreCompleto(),
                    cliente.getTelefono(),
                    cliente.getCorreo(),
                    cliente.getEstado()
                }
        );

        JOptionPane.showMessageDialog(
                null,
                tabla,
                "Clientes",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void buscarPorId() throws Exception {

        Integer id = Integer.valueOf(
                JOptionPane.showInputDialog("ID del cliente:")
        );

        Cliente cliente = clienteController.buscarClientePorId(id);

        if (cliente == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cliente no encontrado."
            );
            return;
        }

        mostrarCliente(cliente);
    }

    private void actualizar() throws Exception {

        Integer id = Integer.valueOf(
                JOptionPane.showInputDialog("ID del cliente a actualizar:")
        );

        Cliente cliente = clienteController.buscarClientePorId(id);

        if (cliente == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Cliente no encontrado."
            );
            return;
        }

        String valor;

        valor = JOptionPane.showInputDialog(
                "Tipo de identificación:",
                cliente.getTipoIdentificacion()
        );
        cliente.setTipoIdentificacion(valor);

        valor = JOptionPane.showInputDialog(
                "Número de identificación:",
                cliente.getNumeroIdentificacion()
        );
        cliente.setNumeroIdentificacion(valor);

        valor = JOptionPane.showInputDialog(
                "Nombre completo:",
                cliente.getNombreCompleto()
        );
        cliente.setNombreCompleto(valor);

        valor = JOptionPane.showInputDialog(
                "Teléfono:",
                cliente.getTelefono()
        );
        cliente.setTelefono(valor);

        valor = JOptionPane.showInputDialog(
                "Correo:",
                cliente.getCorreo()
        );
        cliente.setCorreo(valor);

        valor = JOptionPane.showInputDialog(
                "Dirección:",
                cliente.getDireccion()
        );
        cliente.setDireccion(valor);

        clienteController.actualizarCliente(cliente);

        JOptionPane.showMessageDialog(
                null,
                "Cliente actualizado correctamente."
        );
    }

    private void eliminar() throws Exception {

        Integer id = Integer.valueOf(
                JOptionPane.showInputDialog("ID del cliente a eliminar:")
        );

        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Desea eliminar el cliente con ID " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            clienteController.eliminarCliente(id);

            JOptionPane.showMessageDialog(
                    null,
                    "Cliente eliminado correctamente."
            );
        }
    }

    private void mostrarCliente(Cliente cliente) {

        JOptionPane.showMessageDialog(
                null,
                "ID: " + cliente.getId()
                + "\nTipo identificación: " + cliente.getTipoIdentificacion()
                + "\nNúmero identificación: " + cliente.getNumeroIdentificacion()
                + "\nNombre: " + cliente.getNombreCompleto()
                + "\nTeléfono: " + cliente.getTelefono()
                + "\nCorreo: " + cliente.getCorreo()
                + "\nDirección: " + cliente.getDireccion()
                + "\nEstado: " + cliente.getEstado()
        );
    }
}