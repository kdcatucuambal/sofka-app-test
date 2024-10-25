package com.sofka.lab.customers.app.models.entity;

import com.sofka.lab.common.dtos.ClienteDto;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "tbl_clients")
public class Cliente extends Persona implements Serializable {


    @Column(name = "cli_password", nullable = false)
    private String contrasena;

    @Column(name = "cli_estado", nullable = false)
    private Boolean estado;


    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }


    public ClienteDto toDto() {
        return new ClienteDto(
                this.getId(),
                this.getIdentificacion(),
                this.getNombre(),
                this.getGenero(),
                this.getEdad(),
                this.getDireccion(),
                this.getTelefono(),
                this.getEstado()
        );
    }

    @Serial
    private static final long serialVersionUID = 1L;
}
