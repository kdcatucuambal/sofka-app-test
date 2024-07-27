package com.sofka.lab.customers.app.models.services;

import com.sofka.lab.common.models.dtos.ClienteDto;
import com.sofka.lab.customers.app.models.entity.Cliente;

import java.util.List;

public interface ClienteService {

  List<ClienteDto> findAll();

  ClienteDto findById(Long id);

  ClienteDto save(Cliente cliente);

  ClienteDto update(ClienteDto cliente);

  void delete(Long id);

  ClienteDto findByIdentificacion(String identificacion);


}
