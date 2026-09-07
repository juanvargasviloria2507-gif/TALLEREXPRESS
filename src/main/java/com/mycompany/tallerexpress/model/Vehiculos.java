package com.mycompany.tallerexpress.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Vehiculos {

    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int año;
    private int clienteId;
    private String estado;
    private Date fechaRegistro;
    private Timestamp createdAt;

    public Vehiculos() {
    }

    public Vehiculos(
            String placa,
            String marca,
            String modelo,
            int año,
            int clienteId
    ) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.clienteId = clienteId;
        this.estado = "ACTIVO";
    }

    public Vehiculos(
            int id,
            String placa,
            String marca,
            String modelo,
            int año,
            int clienteId,
            String estado,
            Date fechaRegistro,
            Timestamp createdAt
    ) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.año = año;
        this.clienteId = clienteId;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Vehiculos{"
                + "id=" + id
                + ", placa='" + placa + '\''
                + ", marca='" + marca + '\''
                + ", modelo='" + modelo + '\''
                + ", año=" + año
                + ", clienteId=" + clienteId
                + ", estado='" + estado + '\''
                + ", fechaRegistro=" + fechaRegistro
                + ", createdAt=" + createdAt
                + '}';
    }
}