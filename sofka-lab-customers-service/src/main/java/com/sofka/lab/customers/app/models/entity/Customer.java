package com.sofka.lab.customers.app.models.entity;

import com.sofka.lab.common.dtos.CustomerDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_clients")
public class Customer extends Person implements Serializable {


    @Column(name = "cli_password", nullable = false)
    private String password;

    @Column(name = "cli_estado", nullable = false)
    private Boolean status;

    public CustomerDto toDto() {
        return new CustomerDto(
                this.getId(),
                this.getIdentification(),
                this.getName(),
                this.getGenre(),
                this.getAge(),
                this.getAddress(),
                this.getPhone(),
                this.getStatus()
        );
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
