package com.sofka.lab.accounts.app.accounts.infrastructure.adapter.out.persistence.repository.sequence.impl;

import com.sofka.lab.accounts.app.accounts.domain.ports.out.SequenceRepository;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class AccountSequenceImpl implements SequenceRepository {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long getNextId() {
        String sql = "SELECT nextval('sofka_accounts.tbl_accounts_acc_id_seq')";
        return jdbcTemplate.queryForObject(sql, Long.class);
    }
}
