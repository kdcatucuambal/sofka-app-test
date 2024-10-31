package com.sofka.lab.customers.app.models.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Person implements Serializable {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
