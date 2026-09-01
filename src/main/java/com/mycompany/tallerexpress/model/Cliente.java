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

public class Cliente {
    private Integer id;
    private String documento;
    private String nombre;
    private String telefono;
    private String email;
    private Boolean isActivo;
    private Timestamp createdAt;

    public Cliente() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getDocumento() { return documento; }
    public void setDocumento(String documento) { this.documento = documento; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Boolean getIsActivo() { return isActivo; }
    public void setIsActivo(Boolean isActivo) { this.isActivo = isActivo; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
