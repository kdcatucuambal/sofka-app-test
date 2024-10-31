package com.sofka.lab.customers.app.models.services;

import com.sofka.lab.common.exceptions.BusinessLogicException;
import com.sofka.lab.customers.app.models.dao.CustomerDao;
import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional(readOnly = true)
    public List<CustomerDto> findAll() {
        return customerDao.findDtoAll();
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findById(Long id) {
        if (customerDao.findById(id).isPresent()) {
            return customerDao.findDtoById(id);
        }
        throw new BusinessLogicException("El customer con el ID proporcionado no existe:  " + id, "300");
    }

    @Override
    @Transactional
    public CustomerDto save(CustomerEntity customer) {
        if (customerDao.existsByIdentification(customer.getIdentification())) {
            throw new BusinessLogicException("El prospecto no puede ser creado: " + customer.getIdentification(), "301");
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        var customerSaved = customerDao.save(customer);
        System.out.println(customerSaved);
        return customerDao.findDtoById(customerSaved.getId());
    }

    @Override
    @Transactional
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
    @Transactional
    public void delete(Long id) {
        var customer = this.customerDao
                .findById(id)
                .orElseThrow(() -> new BusinessLogicException("El customer con el ID proporcionado no existe:  " + id, "300"));
        customer.setStatus(false);
        this.customerDao.save(customer);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomerDto findByIdentification(String identification) {
        return customerDao.findDtoByIdentification(identification);
    }
}
