/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.model;

/**
 *
 * @author Coder
 */

import java.math.BigDecimal;
import java.sql.Timestamp;

public class OrdenesServicio {
    private Integer id;
    private Integer clienteId;
    private Integer vehiculoId;
    private String mecanico;
    private Timestamp fechaIngreso;
    private String descripcionProblema;
    private String diagnostico;
    private String estado;
    private BigDecimal costoTotal;

    public OrdenesServicio() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getClienteId() { return clienteId; }
    public void setClienteId(Integer clienteId) { this.clienteId = clienteId; }
    public Integer getVehiculoId() { return vehiculoId; }
    public void setVehiculoId(Integer vehiculoId) { this.vehiculoId = vehiculoId; }
    public String getMecanico() { return mecanico; }
    public void setMecanico(String mecanico) { this.mecanico = mecanico; }
    public Timestamp getFechaIngreso() { return fechaIngreso; }
    public void setFechaIngreso(Timestamp fechaIngreso) { this.fechaIngreso = fechaIngreso; }
    public String getDescripcionProblema() { return descripcionProblema; }
    public void setDescripcionProblema(String descripcionProblema) { this.descripcionProblema = descripcionProblema; }
    public String getDiagnostico() { return diagnostico; }
    public void setDiagnostico(String diagnostico) { this.diagnostico = diagnostico; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public BigDecimal getCostoTotal() { return costoTotal; }
    public void setCostoTotal(BigDecimal costoTotal) { this.costoTotal = costoTotal; }
}
