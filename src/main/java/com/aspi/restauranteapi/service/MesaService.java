package com.aspi.restauranteapi.service;

import com.aspi.restauranteapi.entity.Mesa;
import com.aspi.restauranteapi.entity.Mesa;
import com.aspi.restauranteapi.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {
    @Autowired
    private MesaRepository mesaRepository;
    public List<Mesa> findAll() {
        return mesaRepository.findAll();
    }
    public Mesa findById(Long id) {
        Mesa mesa = mesaRepository.findById(id).get();
        return mesa;
    }
    public ResponseEntity<Mesa> save(Mesa mesa) {
        Mesa mesaSaved = mesaRepository.save(mesa);
        return ResponseEntity.status(HttpStatus.CREATED).body(mesaSaved);
    }
    public ResponseEntity<Mesa> edit(Long id, Mesa mesa) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);
        if (mesaOptional.isPresent()) {
            mesa.setId(id);
            mesaRepository.save(mesa);
            return ResponseEntity.ok(mesa);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Mesa> delete(Long id) {
        Optional<Mesa> mesaOptional = mesaRepository.findById(id);
        if (mesaOptional.isPresent()) {
            mesaRepository.deleteById(id);
            return ResponseEntity.ok(mesaOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
