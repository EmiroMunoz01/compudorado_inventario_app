package com.inventario.backend.repositorio;

import com.inventario.backend.modelo.producto.MemoriaRAM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Repositorio_memoria_ram extends JpaRepository<MemoriaRAM, Long> {
}
