package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.redis.service;

import com.sofka.lab.accounts.app.accounts.domain.ports.out.CustomerQueryRepository;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.redis.mapper.CustomerRedisMapper;
import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.redis.repository.CustomerRedisRepository;
import com.sofka.lab.common.domain.model.CustomerEvent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Adapter de Redis para el repositorio de consulta de clientes
 */

@Component
@AllArgsConstructor
public class CustomerRedisService implements CustomerQueryRepository {

    private final CustomerRedisRepository customerRedisRepository;
    private final CustomerRedisMapper mapper;

    @Override
    public Long save(CustomerEvent customerEvent) {
        var result = customerRedisRepository.save(mapper.toCustomerHash(customerEvent));
        return result.id();
    }

    @Override
    public Long update(CustomerEvent customerEvent) {
        var result = customerRedisRepository.save(mapper.toCustomerHash(customerEvent));
        return result.id();
    }

    @Override
    public Optional<CustomerEvent> findById(Long id) {
        return customerRedisRepository.findById(id).map(mapper::toCustomerEvent);
    }

    @Override
    public Optional<CustomerEvent> findByIdentification(String identification) {
        return customerRedisRepository.findByIdentification(identification).map(mapper::toCustomerEvent);
    }
}