/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.controller;

import com.mycompany.tallerexpress.model.Repuesto;
import com.mycompany.tallerexpress.service.RepuestoService;
import com.mycompany.tallerexpress.service.impl.RepuestoServiceImpl;

import javax.swing.JOptionPane;
import java.util.List;

public class RepuestoController {

    private final RepuestoService repuestoService = new RepuestoServiceImpl();

    public void menuRepuestos() {
        String[] opciones = {"Registrar Repuesto", "Listar/Filtrar Repuestos", "Volver"};
        boolean salir = false;

        while (!salir) {
            int sel = JOptionPane.showOptionDialog(
                    null,
                    "Gestión de Repuestos",
                    "Repuestos",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    opciones,
                    opciones[0]
            );

            switch (sel) {
                case 0 -> registrarRepuesto();
                case 1 -> listarYFiltrar();
                default -> salir = true;
            }
        }
    }

    private void registrarRepuesto() {
        try {
            String codigo = JOptionPane.showInputDialog("Código de Referencia:");
            if (codigo == null || codigo.isBlank()) return;

            String nombre = JOptionPane.showInputDialog("Nombre del Repuesto:");
            if (nombre == null || nombre.isBlank()) return;

            String presentacion = JOptionPane.showInputDialog("Categoría / Presentación:");
            String proveedor = JOptionPane.showInputDialog("Proveedor:");

            String stockStr = JOptionPane.showInputDialog("Stock Inicial:");
            if (stockStr == null || stockStr.isBlank()) return;
            int stock = Integer.parseInt(stockStr);

            String precioStr = JOptionPane.showInputDialog("Precio Unitario:");
            if (precioStr == null || precioStr.isBlank()) return;
            double precio = Double.parseDouble(precioStr);

            Repuesto r = new Repuesto();
            r.setCodigoReferencia(codigo);
            r.setNombre(nombre);
            r.setPresentacion(presentacion);
            r.setProveedor(proveedor);
            r.setStockTotal(stock);
            r.setStockDisponible(stock);
            r.setPrecioUnitario(precio);

            repuestoService.registrar(r);
            JOptionPane.showMessageDialog(null, "Repuesto registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Ingrese valores numéricos válidos en Stock y Precio.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarYFiltrar() {
        try {
            String filtro = JOptionPane.showInputDialog("Ingrese Categoría o Proveedor para filtrar (dejar en blanco para ver todos):");
            if (filtro == null) return;

            List<Repuesto> lista = repuestoService.filtrarPorCategoriaOProveedor(filtro);

            if (lista.isEmpty()) {
                JOptionPane.showMessageDialog(null, "No se encontraron repuestos registrados.", "Sin Resultados", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            StringBuilder sb = new StringBuilder("ID | CÓDIGO | NOMBRE | PRESENTACIÓN | PROVEEDOR | STOCK | PRECIO\n");
            sb.append("------------------------------------------------------------------------------------\n");
            for (Repuesto r : lista) {
                sb.append(String.format(
                        "%d | %s | %s | %s | %s | %d | $%.2f\n",
                        r.getId(),
                        r.getCodigoReferencia(),
                        r.getNombre(),
                        r.getPresentacion(),
                        r.getProveedor(),
                        r.getStockDisponible(),
                        r.getPrecioUnitario()
                ));
            }

            JOptionPane.showMessageDialog(null, sb.toString(), "Inventario de Repuestos", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al consultar repuestos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}