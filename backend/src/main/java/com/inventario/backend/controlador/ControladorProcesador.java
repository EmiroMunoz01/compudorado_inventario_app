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

    @PutMapping("/actualizar/{serial}")
    public ResponseEntity<?> actualizarProcesador(@Valid @PathVariable String serial, @RequestBody ProcesadorDTO procesadorDTO) {

        try {
            ProcesadorDTO actualizarProcesador = servicioProcesador.actualizarProcesador(serial, procesadorDTO);
            return new ResponseEntity<>(actualizarProcesador, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al crear el procesador: " + e.getMessage());
        }

    }


    @GetMapping("/buscar/nombre/{nombreProcesador}")
    public ResponseEntity<?> buscarProcesadorPorNombre(@PathVariable String nombreProcesador) {

        try {
            List<ProcesadorDTO> procesador = servicioProcesador.buscarProcesadorPorNombreContains(nombreProcesador);
            return new ResponseEntity<>(procesador, HttpStatus.FOUND);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("No se ha encontrado el procesador: " + e.getMessage());
        }

    }

    @GetMapping("/buscar/serial/{serialProcesador}")
    public ResponseEntity<?> buscarProcesadorPorSerial(@PathVariable String serialProcesador) {

        try {
            ProcesadorDTO procesador = servicioProcesador.buscarProcesadorPorSerial(serialProcesador);
            return new ResponseEntity<>(procesador, HttpStatus.FOUND);

        } catch (Exception e) {

            return ResponseEntity.internalServerError().body("No se ha encontrado el procesador: " + e.getMessage());
        }

    }

    @DeleteMapping("/eliminar/{serialProcesador}")
    public ResponseEntity<?> eliminarProcesadorPorSerial(@PathVariable String serialProcesador) {
        try {
            servicioProcesador.eliminarProcesador(serialProcesador);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error al eliminar el procesador: " + e.getMessage());
        }
    }

}
