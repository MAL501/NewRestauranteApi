package com.aspi.restauranteapi.controller;

import com.aspi.restauranteapi.entity.Mesa;
import com.aspi.restauranteapi.service.MesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class MesaController {
    @Autowired
    private MesaService mesaService;
    @GetMapping("/mesas")
    public ResponseEntity<List<Mesa>> getAllMesas() {
        List<Mesa> mesas = mesaService.findAll();
        return ResponseEntity.ok(mesas);
    }
    @GetMapping("/mesas/{id}")
    public ResponseEntity<Mesa> getAllMesas(@PathVariable Long id) {
        Mesa mesa = mesaService.findById(id);
        return ResponseEntity.ok(mesa);
    }
    @PostMapping("/mesas")
    public ResponseEntity<Mesa> saveMesa(@RequestBody Mesa mesa) {
        return mesaService.save(mesa);
    }
    @PutMapping("/mesas/{id}")
    public ResponseEntity<Mesa> updateMesa(@RequestBody Mesa mesa, @PathVariable Long id) {
        return mesaService.edit(id, mesa);
    }
    @DeleteMapping("/mesas/{id}")
    public ResponseEntity<Mesa> deleteMesa(@PathVariable Long id) {
        return mesaService.delete(id);
    }
}
