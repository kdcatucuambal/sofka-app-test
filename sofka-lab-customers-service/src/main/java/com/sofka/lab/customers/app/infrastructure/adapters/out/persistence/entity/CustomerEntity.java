package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_clients")
public class CustomerEntity extends PersonEntity {

    @Column(name = "cli_password", nullable = false)
    private String password;

    @Column(name = "cli_estado", nullable = false)
    private Boolean status;

}
