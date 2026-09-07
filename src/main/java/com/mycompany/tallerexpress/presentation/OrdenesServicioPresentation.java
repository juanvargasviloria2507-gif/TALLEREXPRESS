package com.mycompany.tallerexpress.presentation;

import com.mycompany.tallerexpress.controller.OrdenesServicioController;
import com.mycompany.tallerexpress.model.OrdenesServicio;
import com.mycompany.tallerexpress.util.TableFormatter;

import javax.swing.JOptionPane;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class OrdenesServicioPresentation {

    private final OrdenesServicioController ordenesController =
            new OrdenesServicioController();

    public void menu() {

        String opcion;

        do {
            opcion = JOptionPane.showInputDialog(
                    null,
                    """
                    === ÓRDENES DE SERVICIO ===

                    1. Registrar orden
                    2. Consultar historial por vehículo
                    3. Actualizar estado y costo
                    0. Volver
                    """,
                    "Órdenes de servicio",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (opcion == null) {
                return;
            }

            try {
                switch (opcion) {
                    case "1" -> registrarOrden();
                    case "2" -> consultarHistorial();
                    case "3" -> actualizarEstadoYCosto();
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

    private void registrarOrden() throws Exception {

        OrdenesServicio orden = new OrdenesServicio();

        orden.setClienteId(
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                "ID del cliente:"
                        )
                )
        );

        orden.setVehiculoId(
                Integer.parseInt(
                        JOptionPane.showInputDialog(
                                "ID del vehículo:"
                        )
                )
        );

        orden.setMecanicoResponsable(
                JOptionPane.showInputDialog(
                        "Mecánico responsable:"
                )
        );

        String fecha = JOptionPane.showInputDialog(
                "Fecha (YYYY-MM-DD):"
        );

        orden.setFecha(Date.valueOf(fecha));

        orden.setDescripcionProblema(
                JOptionPane.showInputDialog(
                        "Descripción del problema:"
                )
        );

        orden.setDiagnostico(
                JOptionPane.showInputDialog(
                        "Diagnóstico:"
                )
        );

        orden.setEstadoDeLaOrden(
                JOptionPane.showInputDialog(
                        "Estado de la orden:"
                )
        );

        String costo = JOptionPane.showInputDialog(
                "Costo total:"
        );

        if (costo != null && !costo.isBlank()) {
            orden.setCostoTotal(
                    new BigDecimal(costo)
            );
        }

        String repuesto = JOptionPane.showInputDialog(
                "ID del repuesto utilizado (dejar vacío si no aplica):"
        );

        Integer repuestoId = null;
        Integer cantidad = null;

        if (repuesto != null && !repuesto.isBlank()) {

            repuestoId = Integer.parseInt(repuesto);

            cantidad = Integer.parseInt(
                    JOptionPane.showInputDialog(
                            "Cantidad del repuesto:"
                    )
            );
        }

        OrdenesServicio creada =
                ordenesController.registrarOrden(
                        orden,
                        repuestoId,
                        cantidad
                );

        JOptionPane.showMessageDialog(
                null,
                "Orden registrada correctamente.\nID: "
                + creada.getId()
        );
    }

    private void consultarHistorial() throws Exception {

        Integer vehiculoId = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "ID del vehículo:"
                )
        );

        List<OrdenesServicio> ordenes =
                ordenesController.consultarHistorialPorVehiculo(
                        vehiculoId
                );

        String[] headers = {
            "ID",
            "Cliente",
            "Vehículo",
            "Mecánico",
            "Fecha",
            "Estado",
            "Costo"
        };

        String tabla = TableFormatter.formatTable(
                "Historial del vehículo",
                headers,
                ordenes,
                orden -> new String[]{
                    String.valueOf(orden.getId()),
                    String.valueOf(orden.getClienteId()),
                    String.valueOf(orden.getVehiculoId()),
                    orden.getMecanicoResponsable(),
                    String.valueOf(orden.getFecha()),
                    orden.getEstadoDeLaOrden(),
                    String.valueOf(orden.getCostoTotal())
                }
        );

        JOptionPane.showMessageDialog(
                null,
                tabla,
                "Historial",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    private void actualizarEstadoYCosto() throws Exception {

        Integer ordenId = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "ID de la orden:"
                )
        );

        String nuevoEstado = JOptionPane.showInputDialog(
                "Nuevo estado:"
        );

        BigDecimal costoTotal = new BigDecimal(
                JOptionPane.showInputDialog(
                        "Costo total:"
                )
        );

        ordenesController.actualizarEstadoYCosto(
                ordenId,
                nuevoEstado,
                costoTotal
        );

        JOptionPane.showMessageDialog(
                null,
                "Estado y costo actualizados correctamente."
        );
    }
}