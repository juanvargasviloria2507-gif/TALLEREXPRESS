/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tallerexpress.model;

public class Repuesto {
    private Integer id;
    private String codigoReferencia;
    private String nombre;
    private String presentacion;
    private String proveedor;
    private int stockTotal;
    private int stockDisponible;
    private double precioUnitario;

    public Repuesto() {
    }

    public Repuesto(Integer id, String codigoReferencia, String nombre, String presentacion, String proveedor, int stockTotal, int stockDisponible, double precioUnitario) {
        this.id = id;
        this.codigoReferencia = codigoReferencia;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.proveedor = proveedor;
        this.stockTotal = stockTotal;
        this.stockDisponible = stockDisponible;
        this.precioUnitario = precioUnitario;
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
}