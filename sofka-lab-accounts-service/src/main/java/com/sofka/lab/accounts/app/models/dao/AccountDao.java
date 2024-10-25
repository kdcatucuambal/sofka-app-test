package com.sofka.lab.accounts.app.models.dao;

import com.sofka.lab.accounts.app.models.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountDao extends JpaRepository<Account, Long> {


    @Query("select c from Account c where c.number = ?1")
    Account findByNumber(String number);


}
