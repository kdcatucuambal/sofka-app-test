package com.sofka.lab.accounts.app.models.dao;

import com.sofka.lab.accounts.app.models.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CuentaDao extends JpaRepository<Cuenta, Long> {


    @Query("select c from Cuenta c where c.numero = ?1")
    Cuenta findByNumero(String numero);


}
