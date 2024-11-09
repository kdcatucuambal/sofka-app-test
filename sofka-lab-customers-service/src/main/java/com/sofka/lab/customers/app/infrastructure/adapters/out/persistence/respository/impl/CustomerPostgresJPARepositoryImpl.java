package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.respository.impl;

import com.sofka.lab.customers.app.domain.model.CustomerModel;
import com.sofka.lab.customers.app.domain.ports.out.repository.CustomerRepository;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.entity.CustomerEntity;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.mapper.CustomerInfraMapper;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.respository.CustomerJPARepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Repository
@AllArgsConstructor
public class CustomerPostgresJPARepositoryImpl implements CustomerRepository {

    private final CustomerJPARepository customerJPARepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerInfraMapper mapper;


    @Transactional(readOnly = true)
    @Override
    public List<CustomerModel> findAll() {
        return customerJPARepository.findAll().stream().map(mapper::toReadCustomerModel).toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CustomerModel> findById(Long id) {
        return customerJPARepository.findById(id).map(mapper::toReadCustomerModel);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<CustomerModel> findByIdentification(String identification) {
        return customerJPARepository.findByIdentification(identification).map(mapper::toReadCustomerModel);
    }

    @Transactional
    @Override
    public CustomerModel save(CustomerModel customer) {
        var customerEntity = mapper.toCustomerEntity(customer);
        customerEntity.setPassword(passwordEncoder.encode(customer.getPassword()));
        return mapper.toCustomerModel(customerJPARepository.save(customerEntity));
    }

    @Transactional
    @Override
    public void update(CustomerModel customer) {
        var customerEntity = mapper.toCustomerEntity(customer);
        customerJPARepository.customUpdate(customer.getId(), customerEntity);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        var customer = customerJPARepository.findById(id)
                .map(c -> {
                    c.setStatus(Boolean.FALSE);
                    return c;
                });
        Consumer<CustomerEntity> consumerFunction = customerJPARepository::save;
        customer.ifPresent(consumerFunction);

    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsByIdentification(String identification) {
        return customerJPARepository.existsByIdentification(identification);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsById(Long id) {
        return customerJPARepository.existsById(id);
    }
}
