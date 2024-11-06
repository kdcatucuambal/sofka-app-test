package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence;


import com.sofka.lab.customers.app.application.port.out.CustomerPersistencePort;
import com.sofka.lab.customers.app.domain.model.CustomerDomain;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.mapper.CustomerInfraMapper;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.respository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class CustomerPersistenceAdapter implements CustomerPersistencePort {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerInfraMapper customerMapper;


    @Override
    public List<CustomerDomain> findAll() {
        return customerRepository.findAllByStatusTrue()
                .stream()
                .map(customerMapper::toReadCustomerDomain)
                .toList();
    }

    @Override
    public Optional<CustomerDomain> findById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toReadCustomerDomain);
    }

    @Override
    public Optional<CustomerDomain> findByIdentification(String identification) {
        return customerRepository.findByIdentification(identification).map(customerMapper::toReadCustomerDomain);
    }

    @Override
    public CustomerDomain save(CustomerDomain customer) {
        var customerEntity = customerMapper.toCustomerEntity(customer);
        customerEntity.setPassword(passwordEncoder.encode(customerEntity.getPassword()));
        customerEntity = customerRepository.save(customerEntity);
        return customerMapper.toCustomerDomain(customerEntity);
    }

    @Override
    public void update(CustomerDomain customer) {
        var customerEntity = customerMapper.toCustomerEntity(customer);
        customerRepository.customUpdate(customerEntity.getId(), customerEntity);
        customerMapper.toCustomerDomain(customerEntity);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.updateStatusById(id, Boolean.FALSE);
    }


    @Override
    public boolean existsByIdentification(String identification) {
        return customerRepository.existsByIdentification(identification);
    }

    @Override
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }
}
