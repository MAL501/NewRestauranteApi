package com.aspi.restauranteapi.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HorarioDTO {
    private LocalTime hora;
    private List<MesaDTO> mesasDisponibles;
}