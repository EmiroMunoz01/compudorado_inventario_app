package com.inventario.backend.repositorio;

import com.inventario.backend.modelo.Memoria_Ram;
import com.inventario.backend.modelo.Procesador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface repositorio_memoria_ram extends JpaRepository<Memoria_Ram, Long> {
}
