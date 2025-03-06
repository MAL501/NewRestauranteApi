package com.aspi.restauranteapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3, message = "El nombre del cliente debe tener al menos 3 caracteres")
    private String name;

    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    @JsonIgnore
    private List<Reserva> reservas;

    @OneToOne(mappedBy = "cliente")
    private UserEntity userEntity;
}
