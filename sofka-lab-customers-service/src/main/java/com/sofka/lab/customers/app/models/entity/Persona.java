package com.sofka.lab.customers.app.models.entity;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_persons")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Persona implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "per_id", nullable = false)
  private Long id;

  @Column(name = "per_name", nullable = false)
  private String nombre;

  @Column(name = "per_genre", nullable = false)
  private String genero;

  @Column(name = "per_age", nullable = false)
  private Integer edad;

  @Column(name = "per_identification", nullable = false, unique = true)
  private String identificacion;

  @Column(name = "per_address", nullable = false)
  private String direccion;

  @Column(name = "per_phone", nullable = false)
  private String telefono;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getGenero() {
    return genero;
  }

  public void setGenero(String genero) {
    this.genero = genero;
  }

  public Integer getEdad() {
    return edad;
  }

  public void setEdad(Integer edad) {
    this.edad = edad;
  }

  public String getIdentificacion() {
    return identificacion;
  }

  public void setIdentificacion(String identificacion) {
    this.identificacion = identificacion;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  @Serial
  private static final long serialVersionUID = 1L;
}
