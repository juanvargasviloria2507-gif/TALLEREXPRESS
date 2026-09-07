package com.mycompany.tallerexpress.presentation;

import com.mycompany.tallerexpress.controller.RepuestoController;
import com.mycompany.tallerexpress.model.Repuesto;
import com.mycompany.tallerexpress.util.TableFormatter;

import javax.swing.JOptionPane;
import java.util.List;

public class RepuestoPresentation {

    private final RepuestoController repuestoController =
            new RepuestoController();

    public void menu() {

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    null,
                    """
                    === GESTIÓN DE REPUESTOS ===

                    1. Registrar repuesto
                    2. Listar repuestos
                    3. Buscar por ID
                    4. Filtrar por categoría o proveedor
                    5. Actualizar repuesto
                    6. Eliminar repuesto
                    0. Volver
                    """,
                    "Repuestos",
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
                    case "4" -> filtrar();
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

    private void registrar() throws Exception {

        Repuesto repuesto = new Repuesto();

        repuesto.setCodigoReferencia(
                JOptionPane.showInputDialog("Código de referencia:")
        );

        repuesto.setNombre(
                JOptionPane.showInputDialog("Nombre:")
        );

        repuesto.setCategoria(
                JOptionPane.showInputDialog("Categoría:")
        );

        repuesto.setPresentacion(
                JOptionPane.showInputDialog("Presentación:")
        );

        repuesto.setProveedor(
                JOptionPane.showInputDialog("Proveedor:")
        );

        repuesto.setStockTotal(
                Integer.parseInt(
                        JOptionPane.showInputDialog("Stock total:")
                )
        );

        repuesto.setStockDisponible(
                Integer.parseInt(
                        JOptionPane.showInputDialog("Stock disponible:")
                )
        );

        repuesto.setPrecioUnitario(
                Double.parseDouble(
                        JOptionPane.showInputDialog("Precio unitario:")
                )
        );

        Repuesto creado =
                repuestoController.registrarRepuesto(repuesto);

        JOptionPane.showMessageDialog(
                null,
                "Repuesto registrado correctamente.\nID: "
                + creado.getId()
        );
    }

    private void listar() throws Exception {

        List<Repuesto> repuestos =
                repuestoController.listarRepuestos();

        mostrarTabla(repuestos);
    }

    private void buscarPorId() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del repuesto:")
        );

        Repuesto repuesto =
                repuestoController.buscarRepuestoPorId(id);

        if (repuesto == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Repuesto no encontrado."
            );
            return;
        }

        mostrarRepuesto(repuesto);
    }

    private void filtrar() throws Exception {

        String filtro = JOptionPane.showInputDialog(
                "Ingrese categoría o proveedor:"
        );

        List<Repuesto> repuestos =
                repuestoController.filtrarPorCategoriaOProveedor(filtro);

        mostrarTabla(repuestos);
    }

    private void actualizar() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del repuesto:")
        );

        Repuesto repuesto =
                repuestoController.buscarRepuestoPorId(id);

        if (repuesto == null) {
            JOptionPane.showMessageDialog(
                    null,
                    "Repuesto no encontrado."
            );
            return;
        }

        repuesto.setCodigoReferencia(
                JOptionPane.showInputDialog(
                        "Código de referencia:",
                        repuesto.getCodigoReferencia()
                )
        );

        repuesto.setNombre(
                JOptionPane.showInputDialog(
                        "Nombre:",
                        repuesto.getNombre()
                )
        );

        repuesto.setCategoria(
                JOptionPane.showInputDialog(
                        "Categoría:",
                        repuesto.getCategoria()
                )
        );

        repuesto.setPresentacion(
                JOptionPane.showInputDialog(
                        "Presentación:",
                        repuesto.getPresentacion()
                )
        );

        repuesto.setProveedor(
                JOptionPane.showInputDialog(
                        "Proveedor:",
                        repuesto.getProveedor()
                )
        );

        repuesto.setStockTotal(
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                "Stock total:",
                                repuesto.getStockTotal()
                        )
                )
        );

        repuesto.setStockDisponible(
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                "Stock disponible:",
                                repuesto.getStockDisponible()
                        )
                )
        );

        repuesto.setPrecioUnitario(
                Double.parseDouble(
                        JOptionPane.showInputDialog(
                                "Precio unitario:",
                                repuesto.getPrecioUnitario()
                        )
                )
        );

        repuesto.setEstado(
                JOptionPane.showInputDialog(
                        "Estado:",
                        repuesto.getEstado()
                )
        );

        repuestoController.actualizarRepuesto(repuesto);

        JOptionPane.showMessageDialog(
                null,
                "Repuesto actualizado correctamente."
        );
    }

    private void eliminar() throws Exception {

        Integer id = Integer.parseInt(
                JOptionPane.showInputDialog("ID del repuesto:")
        );

        int confirmacion = JOptionPane.showConfirmDialog(
                null,
                "¿Desea eliminar el repuesto con ID " + id + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {

            repuestoController.eliminarRepuesto(id);

            JOptionPane.showMessageDialog(
                    null,
                    "Repuesto eliminado correctamente."
            );
        }
    }

    private void mostrarTabla(List<Repuesto> repuestos) {

        String[] headers = {
            "ID",
            "Código",
            "Nombre",
            "Categoría",
            "Proveedor",
            "Stock",
            "Disponible",
            "Precio",
            "Estado"
        };

        String tabla = TableFormatter.formatTable(
                "Lista de repuestos",
                headers,
                repuestos,
                repuesto -> new String[]{
                    String.valueOf(repuesto.getId()),
                    repuesto.getCodigoReferencia(),
                    repuesto.getNombre(),
                    repuesto.getCategoria(),
                    repuesto.getProveedor(),
                    String.valueOf(repuesto.getStockTotal()),
                    String.valueOf(repuesto.getStockDisponible()),
                    String.valueOf(repuesto.getPrecioUnitario()),
                    repuesto.getEstado()
                }
        );

        JOptionPane.showMessageDialog(
                null,
                tabla,
                "Repuestos",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void mostrarRepuesto(Repuesto repuesto) {

        JOptionPane.showMessageDialog(
                null,
                "ID: " + repuesto.getId()
                + "\nCódigo: " + repuesto.getCodigoReferencia()
                + "\nNombre: " + repuesto.getNombre()
                + "\nCategoría: " + repuesto.getCategoria()
                + "\nPresentación: " + repuesto.getPresentacion()
                + "\nProveedor: " + repuesto.getProveedor()
                + "\nStock total: " + repuesto.getStockTotal()
                + "\nStock disponible: " + repuesto.getStockDisponible()
                + "\nPrecio unitario: " + repuesto.getPrecioUnitario()
                + "\nEstado: " + repuesto.getEstado()
        );
    }
}