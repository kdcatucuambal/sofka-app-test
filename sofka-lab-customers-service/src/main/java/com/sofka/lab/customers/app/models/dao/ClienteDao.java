package com.sofka.lab.customers.app.models.dao;

import com.sofka.lab.common.models.dtos.ClienteDto;
import com.sofka.lab.customers.app.models.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ClienteDao extends JpaRepository<Cliente, Long> {



    @Query("SELECT new com.sofka.lab.common.models.dtos.ClienteDto(c.id, c.identificacion, c.nombre, c.genero, " +
            "c.edad, c.direccion, c.telefono, c.estado) FROM Cliente c WHERE c.id = ?1")
    ClienteDto findByIdDto(Long id);

   @Query("SELECT new com.sofka.lab.common.models.dtos.ClienteDto(c.id, c.identificacion, c.nombre, c.genero, " +
            "c.edad, c.direccion, c.telefono, c.estado) FROM Cliente c")
    List<ClienteDto> findAllDto();

   @Query("SELECT new com.sofka.lab.common.models.dtos.ClienteDto(c.id, c.identificacion, c.nombre, c.genero, " +
            "c.edad, c.direccion, c.telefono, c.estado) FROM Cliente c WHERE c.identificacion = ?1")
   ClienteDto findByIdentificacionDto(String identificacion);


}
