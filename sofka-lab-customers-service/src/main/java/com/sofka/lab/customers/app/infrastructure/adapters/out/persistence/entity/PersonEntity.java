package com.sofka.lab.customers.app.infrastructure.adapters.out.persistence.entity;


import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "per_id", nullable = false)
    private Long id;

    @Column(name = "per_name", nullable = false)
    private String name;

    @Column(name = "per_genre", nullable = false, length = 1)
    private String genre;

    @Column(name = "per_age", nullable = false)
    private Integer age;

    @Column(name = "per_identification", nullable = false, unique = true, length = 13)
    private String identification;

    @Column(name = "per_address", nullable = false)
    private String address;

    @Column(name = "per_phone", nullable = false, length = 15)
    private String phone;


    @Serial
    private static final long serialVersionUID = 1L;


}
