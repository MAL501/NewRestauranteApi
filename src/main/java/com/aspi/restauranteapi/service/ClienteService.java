package com.aspi.restauranteapi.service;

import com.aspi.restauranteapi.dto.ReservaDTO;
import com.aspi.restauranteapi.entity.Cliente;
import com.aspi.restauranteapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }
    public Cliente findById(Long id) {
        Cliente cliente = clienteRepository.findById(id).get();
        return cliente;
    }
    public ResponseEntity<Cliente> save(Cliente cliente) {
        Cliente clienteSaved = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteSaved);
    }
    public ResponseEntity<Cliente> edit(Long id, Cliente cliente) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            cliente.setId(id);
            clienteRepository.save(cliente);
            return ResponseEntity.ok(cliente);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    public ResponseEntity<Cliente> delete(Long id) {
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);
        if (clienteOptional.isPresent()) {
            clienteRepository.deleteById(id);
            return ResponseEntity.ok(clienteOptional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
