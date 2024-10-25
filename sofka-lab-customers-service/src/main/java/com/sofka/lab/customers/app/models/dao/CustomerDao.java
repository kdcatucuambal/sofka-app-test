package com.sofka.lab.customers.app.models.dao;

import com.sofka.lab.common.dtos.CustomerDto;
import com.sofka.lab.customers.app.models.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long> {



    @Query("SELECT new com.sofka.lab.common.dtos.CustomerDto(c.id, c.identification, c.name, c.genre, " +
            "c.age, c.address, c.phone, c.status) FROM Customer c WHERE c.id = ?1")
    CustomerDto findByIdDto(Long id);

   @Query("SELECT new com.sofka.lab.common.dtos.CustomerDto(c.id, c.identification, c.name, c.genre, " +
            "c.age, c.address, c.phone, c.status) FROM Customer c")
    List<CustomerDto> findAllDto();

   @Query("SELECT new com.sofka.lab.common.dtos.CustomerDto(c.id, c.identification, c.name, c.genre, " +
            "c.age, c.address, c.phone, c.status) FROM Customer c WHERE c.identification = ?1")
   CustomerDto findByIdentificationDto(String identification);

   boolean existsByIdentification(String identification);


}
