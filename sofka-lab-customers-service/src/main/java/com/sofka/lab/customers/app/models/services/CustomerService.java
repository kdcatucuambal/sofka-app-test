package com.sofka.lab.customers.app.models.services;

import com.sofka.lab.common.dtos.CustomerDto;
import com.sofka.lab.customers.app.models.entity.Customer;

import java.util.List;

public interface CustomerService {

  List<CustomerDto> findAll();

  CustomerDto findById(Long id);

  CustomerDto save(Customer customer);

  CustomerDto update(CustomerDto customer);

  void delete(Long id);

  CustomerDto findByIdentification(String identification);


}
