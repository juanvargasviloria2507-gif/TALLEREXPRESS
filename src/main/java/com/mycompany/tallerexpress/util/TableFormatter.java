/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.util;

import java.util.List;
import java.util.function.Function;

public class TableFormatter {
    public static <T> String formatTable(String title, String[] headers, List<T> items, Function<T, String[]> rowExtractor) {
        if (items == null || items.isEmpty()) {
            return title + "\n\nNo hay registros disponibles.";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("=== ").append(title.toUpperCase()).append(" ===\n\n");

        for (String header : headers) {
            sb.append(String.format("%-18s", header));
        }
        sb.append("\n").append("-".repeat(headers.length * 18)).append("\n");

        for (T item : items) {
            String[] row = rowExtractor.apply(item);
            for (String cell : row) {
                sb.append(String.format("%-18s", cell));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
