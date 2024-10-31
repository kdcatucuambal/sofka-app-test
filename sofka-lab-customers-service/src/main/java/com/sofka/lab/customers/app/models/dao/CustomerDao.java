package com.sofka.lab.customers.app.models.dao;


import com.sofka.lab.customers.app.models.entity.CustomerEntity;
import com.sofka.lab.customers.app.models.entity.dtos.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<CustomerEntity, Long> {



    @Query("SELECT new " +
            "com.sofka.lab.customers.app.models.entity.dtos.CustomerDto" +
            "(c.id, c.name, c.genre, c.age, c.identification, c.address, c.phone, c.status) " +
            "FROM CustomerEntity c WHERE c.id = ?1")
    CustomerDto findDtoById(Long id);


    @Query("SELECT new com.sofka.lab.customers.app.models.entity.dtos.CustomerDto" +
            "(c.id, c.name, c.genre, c.age, c.identification, c.address, c.phone, c.status) " +
            "FROM CustomerEntity c WHERE c.status = true")
    List<CustomerDto> findDtoAll();

    @Query("SELECT new com.sofka.lab.customers.app.models.entity.dtos.CustomerDto" +
            "(c.id, c.name, c.genre, c.age, c.identification, c.address, c.phone, c.status) " +
            "FROM CustomerEntity c WHERE c.identification = ?1")
    CustomerDto findDtoByIdentification(String identification);


    boolean existsByIdentification(String identification);


}
