package com.sofka.lab.accounts.app.models.dao;

import com.sofka.lab.accounts.app.models.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDao extends JpaRepository<AccountEntity, Long> {


    @Query("select c from AccountEntity c where c.number = ?1")
    AccountEntity findByNumber(String number);

    List<AccountEntity> findByStatusTrue();

}
