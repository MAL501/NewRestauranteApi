package com.aspi.restauranteapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReservaDTOCliente {
    private Long mesaId;
    private LocalDate bookingDate;
    private LocalTime bookingTime;
    private Integer people;
}
