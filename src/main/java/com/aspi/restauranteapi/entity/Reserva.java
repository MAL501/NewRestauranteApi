package com.aspi.restauranteapi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;

import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
/**
 * El uniqueConstraits nos permite que no se puedan insertar reservas en al misma mesa
 * el mismo día, a la misma hora
 */
@Table(name = "reservas", uniqueConstraints = {@UniqueConstraint(columnNames = {"id_mesa","booking_date","booking_time"})})

public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @FutureOrPresent(message = "La reserva no puede ser una fecha pasada")
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private Integer people;
    /**
     * Un cliente puede tener múltiples reservas
     * Una mesa puede tener múltiples reservas
     */
    @ManyToOne(targetEntity = Mesa.class)
    private Mesa mesa;
    @ManyToOne(targetEntity = Cliente.class)
    private Cliente cliente;

    public Reserva() {
    }

    public Reserva(Long id, LocalDate bookingDate, LocalTime bookingTime,Integer people, Mesa mesa, Cliente cliente) {
        this.id = id;
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.people = people;
        this.mesa = mesa;
        this.cliente = cliente;
    }
    public Reserva(LocalDate bookingDate, LocalTime bookingTime,Integer people, Mesa mesa, Cliente cliente) {
        this.bookingDate = bookingDate;
        this.bookingTime = bookingTime;
        this.people = people;
        this.mesa = mesa;
        this.cliente = cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public Integer getPeople() {
        return people;
    }

    public void setPeople(Integer people) {
        this.people = people;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public LocalTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id=" + id +
                ", bookingDate=" + bookingDate +
                ", bookingTime=" + bookingTime +
                ", people=" + people +
                ", mesa=" + mesa +
                ", cliente=" + cliente +
                '}';
    }
}
