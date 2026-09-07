package com.mycompany.tallerexpress.dao;

import com.mycompany.tallerexpress.model.Vehiculos;
import java.util.List;
import java.util.Optional;

public interface VehiculosDao {

    Vehiculos create(Vehiculos vehiculo) throws Exception;

    Optional<Vehiculos> findById(Integer id) throws Exception;

    Optional<Vehiculos> findByPlaca(String placa) throws Exception;

    List<Vehiculos> findByClienteId(Integer clienteId) throws Exception;

    List<Vehiculos> findAll() throws Exception;

    void update(Vehiculos vehiculo) throws Exception;

    void delete(Integer id) throws Exception;
}