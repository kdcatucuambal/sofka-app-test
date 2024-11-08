package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence;


import com.sofka.lab.customers.app.application.port.out.CustomerEventPort;
import com.sofka.lab.customers.app.application.port.out.CustomerPersistencePort;
import com.sofka.lab.customers.app.domain.model.CustomerDomain;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.entity.CustomerEntity;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.mapper.CustomerInfraMapper;
import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.respository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@AllArgsConstructor
@Slf4j
@Component
public class CustomerPersistenceAdapter implements CustomerPersistencePort {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CustomerEventPort customerEventService;
    private final CustomerInfraMapper customerMapper;


    @Override
    @Transactional(readOnly = true)
    public List<CustomerDomain> findAll() {
        return customerRepository.findAllByStatusTrue()
                .stream()
                .map(customerMapper::toReadCustomerDomain)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDomain> findById(Long id) {
        return customerRepository.findById(id).map(customerMapper::toReadCustomerDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDomain> findByIdentification(String identification) {
        return customerRepository.findByIdentification(identification).map(customerMapper::toReadCustomerDomain);
    }

    @Override
    @Transactional
    public CustomerDomain save(CustomerDomain customer) {
        var customerEntity = customerMapper.toCustomerEntity(customer);
        customerEntity.setPassword(passwordEncoder.encode(customerEntity.getPassword()));
        customerEntity = customerRepository.save(customerEntity);
        customerEventService.sendPayload(customerMapper.toCustomerEventDomain(customerEntity));
        return customerMapper.toCustomerDomain(customerEntity);
    }

    @Override
    @Transactional
    public void update(CustomerDomain customer) {
        var customerEntity = customerMapper.toCustomerEntity(customer);
        customerRepository.customUpdate(customerEntity.getId(), customerEntity);
        customerEventService.sendPayload(customerMapper.toCustomerEventDomain(customerEntity));
        customerMapper.toCustomerDomain(customerEntity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        var customer = customerRepository.findById(id).map(c -> {
            c.setStatus(Boolean.FALSE);
            return c;
        });
        Consumer<CustomerEntity> consumerFunction = c -> {
            customerRepository.save(c);
            customerEventService.sendPayload(customerMapper.toCustomerEventDomain(c));
        };
        customer.ifPresent(consumerFunction);
    }


    @Override
    @Transactional(readOnly = true)
    public boolean existsByIdentification(String identification) {
        return customerRepository.existsByIdentification(identification);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return customerRepository.existsById(id);
    }
}
