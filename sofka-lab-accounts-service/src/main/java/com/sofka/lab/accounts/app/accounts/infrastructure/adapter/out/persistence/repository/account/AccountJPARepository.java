package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.repository.account;

import com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountJPARepository extends JpaRepository<AccountEntity, Long> {

    @Query("select c from AccountEntity c where c.number = ?1")
    Optional<AccountEntity> findByNumber(String number);

    @Modifying
    @Transactional
    @Query("UPDATE AccountEntity c SET c.status = ?2 WHERE c.id = ?1")
    void updateStatusById(Long id, boolean status);

    List<AccountEntity> findAllByStatusTrue();





}