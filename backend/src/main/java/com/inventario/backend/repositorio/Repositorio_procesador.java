package com.inventario.backend.repositorio;

import com.inventario.backend.dto.ProcesadorDTO;
import com.inventario.backend.modelo.Procesador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface Repositorio_procesador extends JpaRepository<Procesador, Long> {

    // 1. Búsqueda por nombre (retorna Optional)


    Optional<Procesador> findBySerial(String serial);

    }
