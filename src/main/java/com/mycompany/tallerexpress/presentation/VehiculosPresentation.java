package com.mycompany.tallerexpress.presentation;

import com.mycompany.tallerexpress.controller.VehiculosController;
import com.mycompany.tallerexpress.model.Vehiculos;
import com.mycompany.tallerexpress.util.TableFormatter;

import javax.swing.JOptionPane;
import java.util.List;
import java.util.Optional;

public class VehiculosPresentation {

    private final VehiculosController vehiculosController =
            new VehiculosController();

    public void menu() {

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    null,
                    """
                    === GESTIÓN DE VEHÍCULOS ===

                    1. Registrar vehículo
                    2. Listar vehículos
                    3. Buscar por ID
                    4. Buscar por placa
                    5. Buscar por cliente
                    6. Actualizar vehículo
                    7. Eliminar vehículo
                    0. Volver
                    """,
                    "Vehículos",
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
                    case "4" -> buscarPorPlaca();
                    case "5" -> buscarPorCliente();
                    case "6" -> actualizar();
                    case "7" -> eliminar();
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

        Vehiculos vehiculo = new Vehiculos();

        vehiculo.setPlaca(
                JOptionPane.showInputDialog("Placa:")
        );

        vehiculo.setMarca(
                JOptionPane.showInputDialog("Marca:")
        );

        vehiculo.setModelo(
                JOptionPane.showInputDialog("Modelo:")
        );

        vehiculo.setAño(
                Integer.parseInt(
                        JOptionPane.showInputDialog("Año:")
                )
        );

        vehiculo.setClienteId(
                Integer.parseInt(
                        JOptionPane.showInputDialog("ID del cliente:")
                )
        );

        Vehiculos creado =
                vehiculosController.registrarVehiculo(vehiculo);

        JOptionPane.showMessageDialog(
                null,
                "Vehículo registrado correctamente.\nID: "
                + creado.getId()
        );
    }

    private void listar() throws Exception {

        List<Vehiculos> vehiculos =
                vehiculosController.listarVehiculos();

        String[] headers = {
            "ID",
            "Placa",
            "Marca",
            "Modelo",
            "Año",
            "Cliente",
            "Estado"
        };

        String tabla = TableFormatter.formatTable(
                "Lista de vehículos",
                headers,
                vehiculos,
                vehiculo -> new String[]{
                    String.valueOf(vehiculo.getId()),
                    vehiculo.getPlaca(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    String.valueOf(vehiculo.getAño()),
                    String.valueOf(vehiculo.getClienteId()),
                    vehiculo.getEstado()
                }
        );

        JOptionPane.showMessageDialog(
                null,
                tabla,
                "Vehículos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void buscarPorId() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del vehículo:")
        );

        Optional<Vehiculos> resultado =
                vehiculosController.buscarVehiculoPorId(id);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Vehículo no encontrado."
            );
            return;
        }

        mostrarVehiculo(resultado.get());
    }

    private void buscarPorPlaca() throws Exception {

        String placa = JOptionPane.showInputDialog("Placa:");

        Optional<Vehiculos> resultado =
                vehiculosController.buscarVehiculoPorPlaca(placa);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Vehículo no encontrado."
            );
            return;
        }

        mostrarVehiculo(resultado.get());
    }

    private void buscarPorCliente() throws Exception {

        Integer clienteId = Integer.parseInt(
                JOptionPane.showInputDialog("ID del cliente:")
        );

        List<Vehiculos> vehiculos =
                vehiculosController.listarVehiculosPorCliente(clienteId);

        String[] headers = {
            "ID",
            "Placa",
            "Marca",
            "Modelo",
            "Año",
            "Estado"
        };

        String tabla = TableFormatter.formatTable(
                "Vehículos del cliente",
                headers,
                vehiculos,
                vehiculo -> new String[]{
                    String.valueOf(vehiculo.getId()),
                    vehiculo.getPlaca(),
                    vehiculo.getMarca(),
                    vehiculo.getModelo(),
                    String.valueOf(vehiculo.getAño()),
                    vehiculo.getEstado()
                }
        );

        JOptionPane.showMessageDialog(
                null,
                tabla,
                "Vehículos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void actualizar() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del vehículo:")
        );

        Optional<Vehiculos> resultado =
                vehiculosController.buscarVehiculoPorId(id);

        if (resultado.isEmpty()) {
            JOptionPane.showMessageDialog(
                    null,
                    "Vehículo no encontrado."
            );
            return;
        }

        Vehiculos vehiculo = resultado.get();

        vehiculo.setPlaca(
                JOptionPane.showInputDialog(
                        "Placa:",
                        vehiculo.getPlaca()
                )
        );

        vehiculo.setMarca(
                JOptionPane.showInputDialog(
                        "Marca:",
                        vehiculo.getMarca()
                )
        );

        vehiculo.setModelo(
                JOptionPane.showInputDialog(
                        "Modelo:",
                        vehiculo.getModelo()
                )
        );

        vehiculo.setAño(
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                "Año:",
                                vehiculo.getAño()
                        )
                )
        );

        vehiculo.setClienteId(
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                "ID del cliente:",
                                vehiculo.getClienteId()
                        )
                )
        );

        vehiculo.setEstado(
                JOptionPane.showInputDialog(
                        "Estado:",
                        vehiculo.getEstado()
                )
        );

        vehiculosController.actualizarVehiculo(vehiculo);

        JOptionPane.showMessageDialog(
                null,
                "Vehículo actualizado correctamente."
        );
    }

    private void eliminar() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del vehículo:")
        );

        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Desea eliminar el vehículo con ID " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            vehiculosController.eliminarVehiculo(id);

            JOptionPane.showMessageDialog(
                    null,
                    "Vehículo eliminado correctamente."
            );
        }
    }

    private void mostrarVehiculo(Vehiculos vehiculo) {

        JOptionPane.showMessageDialog(
                null,
                "ID: " + vehiculo.getId()
                + "\nPlaca: " + vehiculo.getPlaca()
                + "\nMarca: " + vehiculo.getMarca()
                + "\nModelo: " + vehiculo.getModelo()
                + "\nAño: " + vehiculo.getAño()
                + "\nCliente ID: " + vehiculo.getClienteId()
                + "\nEstado: " + vehiculo.getEstado()
        );
    }
}