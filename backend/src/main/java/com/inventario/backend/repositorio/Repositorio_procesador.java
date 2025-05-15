package com.inventario.backend.repositorio;

import com.inventario.backend.dto.ProcesadorDTO;
import com.inventario.backend.modelo.Procesador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;


@Repository
public interface Repositorio_procesador extends JpaRepository<Procesador, Long> {

    // 1. Búsqueda por serial (retorna Optional)
    Optional<Procesador> findBySerial(String serial);

    List<Procesador> findByNombre(String nombre);


    // Búsqueda que contiene el texto (insensible a mayúsculas)
    @Query("SELECT p FROM Procesador p WHERE LOWER(p.nombre) LIKE LOWER(concat('%', :nombre,'%'))")
    List<Procesador> buscarPorNombreConteniendo(@Param("nombre") String nombre);

    //metodo para eliminar un procesador basandonos en su serial
    void deleteProcesadorsBySerial(String serial);
}
