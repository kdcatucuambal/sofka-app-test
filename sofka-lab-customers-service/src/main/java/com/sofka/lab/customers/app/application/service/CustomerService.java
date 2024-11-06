package com.sofka.lab.customers.app.application.service;

import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.application.port.in.CustomerServicePort;
import com.sofka.lab.customers.app.application.port.out.CustomerPersistencePort;
import com.sofka.lab.customers.app.domain.model.CustomerDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CustomerService implements CustomerServicePort {

    private final CustomerPersistencePort customerPersistencePort;

    @Override
    public List<CustomerDomain> findAll() {
        return customerPersistencePort.findAll();
    }

    @Override
    public CustomerDomain findById(Long id) {
        return customerPersistencePort.findById(id).orElseThrow(() -> new BusinessLogicException(3000));
    }

    @Override
    public CustomerDomain findByIdentification(String identification) {
        return customerPersistencePort.findByIdentification(identification).orElseThrow(() -> new BusinessLogicException(3000));
    }

    @Override
    public CustomerDomain save(CustomerDomain customer) {
        var existUser = customerPersistencePort.existsByIdentification(customer.getIdentification());
        if (existUser) throw new BusinessLogicException(3001);
        return customerPersistencePort.save(customer);
    }

    @Override
    public CustomerDomain update(Long id, CustomerDomain customer) {
        var customerFound = customerPersistencePort.findById(id).orElseThrow(() -> new BusinessLogicException(3000));;
        customerFound.setId(id);
        customerFound.setPhone(customer.getPhone() != null ? customer.getPhone() : customerFound.getPhone());
        customerFound.setName(customer.getName() != null ? customer.getName() : customerFound.getName());
        customerFound.setAddress(customer.getAddress() != null ? customer.getAddress() : customerFound.getAddress());
        customerFound.setAge(customer.getAge() != null ? customer.getAge() : customerFound.getAge());
        customerFound.setGenre(customer.getGenre() != null ? customer.getGenre() : customerFound.getGenre());
        customerFound.setStatus(customer.getStatus() != null ? customer.getStatus() : customerFound.getStatus());
        this.customerPersistencePort.update(customerFound);
        return customerFound;
    }

    @Override
    public void deleteById(Long id) {
        var customer = customerPersistencePort.findById(id).orElseThrow(() -> new BusinessLogicException(30000));
        if (!customer.getStatus()) throw new BusinessLogicException(3002);
       this.customerPersistencePort.deleteById(id);
    }



}
