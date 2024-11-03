package com.sofka.lab.accounts.app.models.service.utils;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UtilServiceImpl implements UtilService {

    private final JdbcTemplate jdbcTemplate;

    public UtilServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Long getSequence(String sequenceName) {
        return jdbcTemplate.queryForObject("SELECT nextval('" + sequenceName + "')", Long.class);
    }
}
