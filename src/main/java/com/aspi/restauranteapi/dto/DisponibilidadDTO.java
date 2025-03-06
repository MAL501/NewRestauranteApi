package com.aspi.restauranteapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DisponibilidadDTO {
    private LocalDate fecha;
    private List<HorarioDTO> horarios;
}

