package com.mycompany.tallerexpress.service.impl;

import com.mycompany.tallerexpress.dao.VehiculosDao;
import com.mycompany.tallerexpress.dao.impl.VehiculosImpl;
import com.mycompany.tallerexpress.exception.BusinessException;
import com.mycompany.tallerexpress.model.Vehiculos;
import com.mycompany.tallerexpress.service.VehiculosService;
import com.mycompany.tallerexpress.util.HttpLogger;

import java.util.List;
import java.util.Optional;

public class VehiculosServiceImpl implements VehiculosService {

    private final VehiculosDao vehiculosDao = new VehiculosImpl();

    @Override
    public Vehiculos create(Vehiculos vehiculo) throws Exception {

        HttpLogger.log(
                "POST",
                "/api/vehiculos",
                201,
                "Registrando vehículo"
        );

        if (vehiculo == null) {
            throw new BusinessException("El vehículo es obligatorio.");
        }

        if (vehiculo.getPlaca() == null
                || vehiculo.getPlaca().isBlank()) {
            throw new BusinessException("La placa es obligatoria.");
        }

        if (vehiculo.getMarca() == null
                || vehiculo.getMarca().isBlank()) {
            throw new BusinessException("La marca es obligatoria.");
        }

        if (vehiculo.getModelo() == null
                || vehiculo.getModelo().isBlank()) {
            throw new BusinessException("El modelo es obligatorio.");
        }

        if (vehiculo.getAño() <= 0) {
            throw new BusinessException("El año del vehículo no es válido.");
        }

        if (vehiculo.getClienteId() <= 0) {
            throw new BusinessException("El ID del cliente no es válido.");
        }

        if (vehiculosDao.findByPlaca(vehiculo.getPlaca()).isPresent()) {
            throw new BusinessException(
                    "La placa " + vehiculo.getPlaca()
                    + " ya está registrada en el sistema."
            );
        }

        if (vehiculo.getEstado() == null
                || vehiculo.getEstado().isBlank()) {
            vehiculo.setEstado("ACTIVO");
        }

        return vehiculosDao.create(vehiculo);
    }

    @Override
    public Optional<Vehiculos> findById(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new BusinessException("El ID del vehículo no es válido.");
        }

        HttpLogger.log(
                "GET",
                "/api/vehiculos/" + id,
                200,
                "Consultando vehículo"
        );

        return vehiculosDao.findById(id);
    }

    @Override
    public Optional<Vehiculos> findByPlaca(String placa) throws Exception {

        if (placa == null || placa.isBlank()) {
            throw new BusinessException("La placa es obligatoria.");
        }

        HttpLogger.log(
                "GET",
                "/api/vehiculos/placa/" + placa,
                200,
                "Consultando vehículo por placa"
        );

        return vehiculosDao.findByPlaca(placa);
    }

    @Override
    public List<Vehiculos> findByClienteId(Integer clienteId) throws Exception {

        if (clienteId == null || clienteId <= 0) {
            throw new BusinessException("El ID del cliente no es válido.");
        }

        HttpLogger.log(
                "GET",
                "/api/vehiculos/cliente/" + clienteId,
                200,
                "Consultando vehículos por cliente"
        );

        return vehiculosDao.findByClienteId(clienteId);
    }

    @Override
    public List<Vehiculos> findAll() throws Exception {

        HttpLogger.log(
                "GET",
                "/api/vehiculos",
                200,
                "Listando vehículos"
        );

        return vehiculosDao.findAll();
    }

    @Override
    public void update(Vehiculos vehiculo) throws Exception {

        if (vehiculo == null) {
            throw new BusinessException("El vehículo es obligatorio.");
        }

        if (vehiculo.getId() <= 0) {
            throw new BusinessException("El ID del vehículo no es válido.");
        }

        if (vehiculo.getPlaca() == null
                || vehiculo.getPlaca().isBlank()) {
            throw new BusinessException("La placa es obligatoria.");
        }

        if (vehiculo.getMarca() == null
                || vehiculo.getMarca().isBlank()) {
            throw new BusinessException("La marca es obligatoria.");
        }

        if (vehiculo.getModelo() == null
                || vehiculo.getModelo().isBlank()) {
            throw new BusinessException("El modelo es obligatorio.");
        }

        if (vehiculo.getAño() <= 0) {
            throw new BusinessException("El año del vehículo no es válido.");
        }

        if (vehiculo.getClienteId() <= 0) {
            throw new BusinessException("El ID del cliente no es válido.");
        }

        Optional<Vehiculos> vehiculoExistente =
                vehiculosDao.findByPlaca(vehiculo.getPlaca());

        if (vehiculoExistente.isPresent()
                && vehiculoExistente.get().getId() != vehiculo.getId()) {

            throw new BusinessException(
                    "La placa " + vehiculo.getPlaca()
                    + " ya está registrada en el sistema."
            );
        }

        HttpLogger.log(
                "PUT",
                "/api/vehiculos/" + vehiculo.getId(),
                200,
                "Actualizando vehículo"
        );

        vehiculosDao.update(vehiculo);
    }

    @Override
    public void delete(Integer id) throws Exception {

        if (id == null || id <= 0) {
            throw new BusinessException("El ID del vehículo no es válido.");
        }

        HttpLogger.log(
                "DELETE",
                "/api/vehiculos/" + id,
                200,
                "Eliminando vehículo"
        );

        vehiculosDao.delete(id);
    }
}