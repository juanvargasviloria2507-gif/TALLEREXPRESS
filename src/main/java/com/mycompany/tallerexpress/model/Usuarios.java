/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.model;

/**
 *
 * @author Coder
 */

import java.sql.Timestamp;

public class Usuarios {
    private Integer id;
    private String username;
    private String password;
    private String role;
    private String estado;
    private Timestamp createdAt;

    public Usuarios() {}

    public Usuarios(Integer id, String username, String password, String role, String estado, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.estado = estado;
        this.createdAt = createdAt;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
