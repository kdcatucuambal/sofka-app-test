package com.sofka.lab.customers.app.models.services;

import com.sofka.lab.common.dtos.CustomerDto;
import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.models.dao.CustomerDao;
import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;
    private final PasswordEncoder passwordEncoder;

    public CustomerServiceImpl(CustomerDao customerDao, PasswordEncoder passwordEncoder) {
        this.customerDao = customerDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<CustomerDto> findAll() {
        return customerDao.findAllDto();
    }

    @Override
    public CustomerDto findById(Long id) {
        if (customerDao.findById(id).isPresent()) {
            return customerDao.findByIdDto(id);
        }
        throw new BusinessLogicException("El customer con el ID proporcionado no existe:  " + id, "300");
    }

    @Override
    public CustomerDto save(CustomerEntity customer) {
        if (customerDao.existsByIdentification(customer.getIdentification())) {
            throw new BusinessLogicException("El prospecto no puede ser creado: " + customer.getIdentification(), "301");
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customerDao.save(customer);
        return customer.toDto();
    }

    @Override
    public CustomerDto update(CustomerDto customer) {
        this.customerDao.findById(customer.getId()).ifPresent(customer1 -> {
            customer1.setName(customer.getName());
            customer1.setGenre(customer.getGenre());
            customer1.setAge(customer.getAge());
            customer1.setAddress(customer.getAddress());
            customer1.setPhone(customer.getPhone());
            customer1.setStatus(customer.getStatus());
            customerDao.save(customer1);
        });
        return customer;
    }

    @Override
    public void delete(Long id) {
        var customer = this.customerDao
                .findById(id)
                .orElseThrow(() -> new BusinessLogicException("El customer con el ID proporcionado no existe:  " + id, "300"));
        customer.setStatus(false);
        this.customerDao.save(customer);
    }

    @Override
    public CustomerDto findByIdentification(String identification) {
        return customerDao.findByIdentificationDto(identification);
    }
}
