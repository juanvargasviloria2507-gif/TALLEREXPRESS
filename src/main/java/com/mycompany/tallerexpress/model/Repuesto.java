package com.mycompany.tallerexpress.model;

import java.sql.Date;
import java.sql.Timestamp;

public class Repuesto {

    private Integer id;
    private String codigoReferencia;
    private String nombre;
    private String categoria;
    private String presentacion;
    private String proveedor;
    private int stockTotal;
    private int stockDisponible;
    private double precioUnitario;
    private String estado;
    private Date fechaRegistro;
    private Timestamp createdAt;

    public Repuesto() {
    }

    public Repuesto(Integer id, String codigoReferencia, String nombre,
            String categoria, String presentacion, String proveedor,
            int stockTotal, int stockDisponible, double precioUnitario,
            String estado, Date fechaRegistro, Timestamp createdAt) {

        this.id = id;
        this.codigoReferencia = codigoReferencia;
        this.nombre = nombre;
        this.categoria = categoria;
        this.presentacion = presentacion;
        this.proveedor = proveedor;
        this.stockTotal = stockTotal;
        this.stockDisponible = stockDisponible;
        this.precioUnitario = precioUnitario;
        this.estado = estado;
        this.fechaRegistro = fechaRegistro;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigoReferencia() {
        return codigoReferencia;
    }

    public void setCodigoReferencia(String codigoReferencia) {
        this.codigoReferencia = codigoReferencia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(int stockTotal) {
        this.stockTotal = stockTotal;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
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
}