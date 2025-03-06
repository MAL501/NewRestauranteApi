package com.aspi.restauranteapi.controller;

import com.aspi.restauranteapi.entity.Cliente;
import com.aspi.restauranteapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clientes = clienteService.findAll();
        return ResponseEntity.ok(clientes);
    }
    @GetMapping("/clientes/{id}")
    public ResponseEntity<Cliente> getAllClientes(@PathVariable Long id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok(cliente);
    }
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }
    @PutMapping("/clientes/{id}")
    public ResponseEntity<Cliente> updateCliente(@RequestBody Cliente cliente, @PathVariable Long id) {
        return clienteService.edit(id, cliente);
    }
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Cliente> deleteCliente(@PathVariable Long id) {
        return clienteService.delete(id);
    }

}
