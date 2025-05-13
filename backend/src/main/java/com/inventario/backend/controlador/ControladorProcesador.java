package com.inventario.backend.controlador;


import com.inventario.backend.dto.ProcesadorDTO;
import com.inventario.backend.servicio.procesador.ServicioProcesador;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("compudorado/procesador")
public class ControladorProcesador {

    private final ServicioProcesador servicioProcesador;

    public ControladorProcesador(ServicioProcesador servicioProcesador) {
        this.servicioProcesador = servicioProcesador;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ProcesadorDTO>> mostrarProcesadores() {
        List<ProcesadorDTO> procesadores = servicioProcesador.listarProcesadores();
        return ResponseEntity.ok(procesadores);
    }


    @PostMapping("/crear")
    public ResponseEntity<?> crearProcesador(@Valid @RequestBody ProcesadorDTO procesadorDTO) {
        try {
            ProcesadorDTO procesadorCreado = servicioProcesador.crearProcesador(procesadorDTO);
            return new ResponseEntity<>(procesadorCreado, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el procesador: " + e.getMessage());
        }
    }


}
