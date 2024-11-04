package com.sofka.lab.customers.app.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_clients")
public class CustomerEntity extends Person implements Serializable {

    @Column(name = "cli_password", nullable = false)
    private String password;

    @Column(name = "cli_estado", nullable = false)
    private Boolean status;

    @Serial
    private static final long serialVersionUID = 1L;
}
