/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.model.Cliente;
import com.mycompany.tallerexpress.service.ClienteService;
import com.mycompany.tallerexpress.service.impl.ClienteServiceImpl;

import javax.swing.JOptionPane;
import java.sql.Timestamp;
import java.util.List;

public class ClienteController {

    private final ClienteService clienteService = new ClienteServiceImpl();

    public void iniciar() {
        String[] opciones = {"Registrar Cliente", "Listar Clientes", "Volver"};
        boolean salir = false;

        while (!salir) {
            int sel = JOptionPane.showOptionDialog(
                    null,
                    "Gestión de Clientes",
                    "Clientes",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (sel) {
                case 0 -> registrarCliente();
                case 1 -> listarClientes();
                default -> salir = true;
            }
        }
    }

    private void registrarCliente() {
        try {
            String doc = JOptionPane.showInputDialog("Número de Documento:");
            if (doc == null || doc.isBlank()) return;

            String nombre = JOptionPane.showInputDialog("Nombre Completo:");
            if (nombre == null || nombre.isBlank()) return;

            String tel = JOptionPane.showInputDialog("Teléfono:");
            String email = JOptionPane.showInputDialog("Correo Electrónico (Email):");

            Cliente c = new Cliente();
            c.setDocumento(doc);
            c.setNombre(nombre);
            c.setTelefono(tel);
            c.setEmail(email);
            c.setIsActivo(true);
            c.setCreatedAt(new Timestamp(System.currentTimeMillis()));

            clienteService.create(c);
            JOptionPane.showMessageDialog(null, "Cliente registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarClientes() {
        try {
            List<Cliente> lista = clienteService.findAll();

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No hay clientes registrados.", "Información", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            StringBuilder sb = new StringBuilder("ID | DOCUMENTO | NOMBRE | TELÉFONO | EMAIL | ACTIVO | FECHA CREACIÓN\n");
            sb.append("--------------------------------------------------------------------------------------------------------\n");
            for (Cliente c : lista) {
                String estado = (c.getIsActivo() != null && c.getIsActivo()) ? "SÍ" : "NO";
                sb.append(String.format("%d | %s | %s | %s | %s | %s | %s\n",
                        c.getId(),
                        c.getDocumento(),
                        c.getNombre(),
                        c.getTelefono(),
                        c.getEmail(),
                        estado,
                        c.getCreatedAt() != null ? c.getCreatedAt().toString() : "N/A"
                ));
            }

            JOptionPane.showMessageDialog(null, sb.toString(), "Listado de Clientes", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al listar clientes: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
