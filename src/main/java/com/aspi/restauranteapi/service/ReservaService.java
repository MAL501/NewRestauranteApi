package com.aspi.restauranteapi.service;

import com.aspi.restauranteapi.config.JwtTokenProvider;
import com.aspi.restauranteapi.dto.*;
import com.aspi.restauranteapi.entity.Cliente;
import com.aspi.restauranteapi.entity.Mesa;
import com.aspi.restauranteapi.entity.Reserva;
import com.aspi.restauranteapi.entity.UserEntity;
import com.aspi.restauranteapi.repository.ClienteRepository;
import com.aspi.restauranteapi.repository.MesaRepository;
import com.aspi.restauranteapi.repository.ReservaRepository;
import com.aspi.restauranteapi.repository.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaService {
    @Autowired
    private ReservaRepository reservaRepository;
    @Autowired
    private MesaRepository mesaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private UserEntityRepository userEntityRepository;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    private static final List<LocalTime> HORARIOS_DISPONIBLES = List.of(
            LocalTime.of(13, 0),
            LocalTime.of(14, 0),
            LocalTime.of(15, 0),
            LocalTime.of(16, 0),
            LocalTime.of(19, 0),
            LocalTime.of(20, 0),
            LocalTime.of(21, 0),
            LocalTime.of(22, 0)
    );
    public List<DisponibilidadDTO> obtenerDisponibilidad() {
        List<DisponibilidadDTO> disponibilidad = new ArrayList<>();
        List<Mesa> todasLasMesas = mesaRepository.findAll();

        for (int i = 0; i < 7; i++) {
            LocalDate fecha = LocalDate.now().plusDays(i);
            List<HorarioDTO> horariosDisponibles = new ArrayList<>();

            for (LocalTime horario : HORARIOS_DISPONIBLES) {
                List<Reserva> reservas = reservaRepository.findReservasByFechaYHora(fecha, horario);

                if (reservas.size() < todasLasMesas.size()) { // Aún hay mesas libres
                    List<MesaDTO> mesasLibres = todasLasMesas.stream()
                            .filter(mesa -> reservas.stream().noneMatch(reserva -> reserva.getMesa().equals(mesa)))
                            .map(m -> new MesaDTO(m.getId(), m.getNumber()))
                            .collect(Collectors.toList());

                    horariosDisponibles.add(new HorarioDTO(horario, mesasLibres));
                }
            }

            disponibilidad.add(new DisponibilidadDTO(fecha, horariosDisponibles));
        }
        return disponibilidad;
    }
    public List<ReservaDTO> findAll() {
        List<ReservaDTO> reservas = reservaRepository.findAllDTO();
        return reservas;
    }

    public List<ReservaDTO> findAllByDate(LocalDate day) {
        List<ReservaDTO> reservas = reservaRepository.findAllDTO(day);
        return reservas;
    }
    public ReservaDTO findById(Long id) {
        ReservaDTO reserva = reservaRepository.findAllDTO(id);
        return reserva;
    }
    public ResponseEntity<Reserva> save(ReservaDTOCliente reservaDTOCliente, String token) {
        Reserva reserva = new Reserva();
        String username = jwtTokenProvider.getUsernameFromToken(token.replace("Bearer ", ""));
        UserEntity userEntity = userEntityRepository.findByUsername(username).get();
        Cliente cliente = clienteRepository.findByName(userEntity.getUsername())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        Mesa mesa = mesaRepository.findById(reservaDTOCliente.getMesaId()).get();
        reserva.setCliente(cliente);
        reserva.setMesa(mesa);
        reserva.setPeople(reservaDTOCliente.getPeople());
        reserva.setBookingDate(reservaDTOCliente.getBookingDate());
        reserva.setBookingTime(reservaDTOCliente.getBookingTime());
        Reserva reservaSaved = reservaRepository.save(reserva);

        return ResponseEntity.status(HttpStatus.CREATED).body(reservaSaved);
    }
    public ResponseEntity<Reserva> edit(Long id, Reserva reserva) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        if (reservaOptional.isPresent()) {
            reserva.setId(id);
            reservaRepository.save(reserva);
            return ResponseEntity.ok(reserva);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Reserva> delete(Long id) {
        Optional<Reserva> reservaOptional = reservaRepository.findById(id);
        if (reservaOptional.isPresent()) {
            reservaRepository.deleteById(id);
            return ResponseEntity.ok(reservaOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
