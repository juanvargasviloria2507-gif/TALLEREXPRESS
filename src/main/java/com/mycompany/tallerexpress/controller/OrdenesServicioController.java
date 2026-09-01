/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.dao.OrdenesServicioDao;
import com.mycompany.tallerexpress.dao.impl.OrdenesServicioImpl;
import com.mycompany.tallerexpress.model.OrdenesServicio;
import com.mycompany.tallerexpress.util.TableFormatter;

import javax.swing.*;
import java.math.BigDecimal;
import java.util.List;

public class OrdenesServicioController {
    private final OrdenesServicioDao ordenesDao = new OrdenesServicioImpl();

    public void menuOrdenes() {
        String[] options = {"Nueva Orden de Servicio", "Consultar Historial por Vehículo", "Volver"};
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(null, "Gestión de Órdenes de Servicio", "TallerExpress",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            try {
                switch (choice) {
                    case 0 -> registrarOrden();
                    case 1 -> consultarHistorial();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (choice != 2 && choice != -1);
    }

    private void registrarOrden() throws Exception {
        int clienteId = Integer.parseInt(JOptionPane.showInputDialog("ID Cliente:"));
        int vehiculoId = Integer.parseInt(JOptionPane.showInputDialog("ID Vehículo:"));
        String mecanico = JOptionPane.showInputDialog("Mecánico Asignado:");
        String problema = JOptionPane.showInputDialog("Descripción del Problema:");
        String diagnostico = JOptionPane.showInputDialog("Diagnóstico Inicial:");
        BigDecimal costo = new BigDecimal(JOptionPane.showInputDialog("Costo Estimado/Total:"));

        String inputRepuesto = JOptionPane.showInputDialog("ID Repuesto a Utilizar (Dejar en blanco si ninguno):");
        Integer repuestoId = (inputRepuesto != null && !inputRepuesto.isBlank()) ? Integer.parseInt(inputRepuesto) : null;
        
        int cantidad = 0;
        if (repuestoId != null) {
            cantidad = Integer.parseInt(JOptionPane.showInputDialog("Cantidad de Repuesto:"));
        }

        OrdenesServicio orden = new OrdenesServicio();
        orden.setClienteId(clienteId);
        orden.setVehiculoId(vehiculoId);
        orden.setMecanico(mecanico);
        orden.setDescripcionProblema(problema);
        orden.setDiagnostico(diagnostico);
        orden.setEstado("PENDIENTE");
        orden.setCostoTotal(costo);

        ordenesDao.registrarOrdenTransaccional(orden, repuestoId, cantidad);
        JOptionPane.showMessageDialog(null, "Orden de Servicio #" + orden.getId() + " registrada con éxito (Transacción Realizada).");
    }

    private void consultarHistorial() throws Exception {
        int vehiculoId = Integer.parseInt(JOptionPane.showInputDialog("ID Vehículo:"));
        List<OrdenesServicio> lista = ordenesDao.findByVehiculo(vehiculoId);

        String[] headers = {"ID Orden", "Mecánico", "Estado", "Costo", "Problema"};
        String table = TableFormatter.formatTable("Historial de Reparaciones", headers, lista, o -> new String[]{
                String.valueOf(o.getId()),
                o.getMecanico(),
                o.getEstado(),
                "$" + o.getCostoTotal(),
                o.getDescripcionProblema()
        });

        JTextArea textArea = new JTextArea(table);
        textArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.PLAIN, 12));
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Órdenes de Servicio", JOptionPane.PLAIN_MESSAGE);
    }
}