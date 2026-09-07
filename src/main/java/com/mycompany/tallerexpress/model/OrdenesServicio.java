package com.mycompany.tallerexpress.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class OrdenesServicio {

    private Integer id;
    private Integer clienteId;
    private Integer vehiculoId;
    private Integer repuestoUtilizado;
    private String mecanicoResponsable;
    private Date fecha;
    private String descripcionProblema;
    private String diagnostico;
    private String estadoDeLaOrden;
    private BigDecimal costoTotal;
    private Timestamp fechaCreacion;

    public OrdenesServicio() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClienteId() {
        return clienteId;
    }

    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    public Integer getVehiculoId() {
        return vehiculoId;
    }

    public void setVehiculoId(Integer vehiculoId) {
        this.vehiculoId = vehiculoId;
    }

    public Integer getRepuestoUtilizado() {
        return repuestoUtilizado;
    }

    public void setRepuestoUtilizado(Integer repuestoUtilizado) {
        this.repuestoUtilizado = repuestoUtilizado;
    }

    public String getMecanicoResponsable() {
        return mecanicoResponsable;
    }

    public void setMecanicoResponsable(String mecanicoResponsable) {
        this.mecanicoResponsable = mecanicoResponsable;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcionProblema() {
        return descripcionProblema;
    }

    public void setDescripcionProblema(String descripcionProblema) {
        this.descripcionProblema = descripcionProblema;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getEstadoDeLaOrden() {
        return estadoDeLaOrden;
    }

    public void setEstadoDeLaOrden(String estadoDeLaOrden) {
        this.estadoDeLaOrden = estadoDeLaOrden;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public Timestamp getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Timestamp fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}