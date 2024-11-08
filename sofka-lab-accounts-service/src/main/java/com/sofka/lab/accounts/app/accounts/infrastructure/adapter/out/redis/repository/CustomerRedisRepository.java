package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.redis.repository;

import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.redis.hash.CustomerHash;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CustomerRedisRepository extends CrudRepository<CustomerHash, Long> {

    Optional<CustomerHash> findByIdentification(String identification);

}
