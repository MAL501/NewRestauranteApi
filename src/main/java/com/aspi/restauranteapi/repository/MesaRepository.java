package com.aspi.restauranteapi.repository;

import com.aspi.restauranteapi.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MesaRepository extends JpaRepository<Mesa,Long> {
}
