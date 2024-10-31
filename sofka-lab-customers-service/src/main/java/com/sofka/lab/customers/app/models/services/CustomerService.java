package com.sofka.lab.customers.app.models.services;

import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerDto;

import java.util.List;

public interface CustomerService {

  List<CustomerDto> findAll();

  CustomerDto findById(Long id);

  CustomerDto save(CustomerEntity customer);

  CustomerDto update(CustomerDto customer);

  void delete(Long id);

  CustomerDto findByIdentification(String identification);


}
