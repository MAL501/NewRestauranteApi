package com.aspi.restauranteapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;

import java.time.LocalDate;

public class ReservaDTO {
    
    private String nombre;
    private String email;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fecha;
    private String numero_mesa;

    public ReservaDTO() {}
    public ReservaDTO(String nombre, String email, LocalDate fecha, String numero_mesa) {
        this.nombre = nombre;
        this.email = email;
        this.fecha = fecha;
        this.numero_mesa = numero_mesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getNumero_mesa() {
        return numero_mesa;
    }

    public void setNumero_mesa(String numero_mesa) {
        this.numero_mesa = numero_mesa;
    }

    @Override
    public String toString() {
        return "ReservaDTO{" +
                "nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", fecha=" + fecha +
                ", numero_mesa='" + numero_mesa + '\'' +
                '}';
    }
}
