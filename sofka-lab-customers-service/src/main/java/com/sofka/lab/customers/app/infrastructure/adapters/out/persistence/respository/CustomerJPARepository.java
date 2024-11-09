package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.respository;

import com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface CustomerJPARepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByIdentification(String identification);

    List<CustomerEntity> findAllByStatusTrue();

    boolean existsByIdentification(String identification);


    @Modifying
    @Transactional
    @Query("UPDATE CustomerEntity c SET c.status = ?2 WHERE c.id = ?1")
    void updateStatusById(Long id, boolean status);

    @Modifying
    @Transactional
    @Query("UPDATE CustomerEntity c SET " +
            "c.name = :#{#customer.name}, " +
            "c.address = :#{#customer.address}, " +
            "c.phone = :#{#customer.phone}, " +
            "c.genre = :#{#customer.genre}, " +
            "c.age = :#{#customer.age}, " +
            "c.status = :#{#customer.status} " +
            "WHERE c.id = :id")
    void customUpdate(Long id, CustomerEntity customer);

}
