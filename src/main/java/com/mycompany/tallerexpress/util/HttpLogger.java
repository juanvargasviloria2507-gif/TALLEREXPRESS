/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.util;

import java.time.LocalDateTime;

public class HttpLogger {
    public static void log(String method, String endpoint, int statusCode, String message) {
        System.out.println(String.format("[%s] HTTP %s -> %s | Status: %d | Info: %s",
                LocalDateTime.now(), method, endpoint, statusCode, message));
    }
}
