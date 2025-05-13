package com.inventario.backend.servicio.procesador;

import com.inventario.backend.dto.ProcesadorDTO;
import com.inventario.backend.modelo.Procesador;
import com.inventario.backend.repositorio.Repositorio_procesador;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class ServicioProcesador implements IProcesador {

    private final Repositorio_procesador repositorio_procesador;

    public ServicioProcesador(Repositorio_procesador repositorio_procesador) {
        this.repositorio_procesador = repositorio_procesador;
    }


    //convertiremos el procesador en un DTO
    private ProcesadorDTO convertirProcesadorDTO(Procesador procesador) {
        ProcesadorDTO dto = new ProcesadorDTO();
        dto.setNombre(procesador.getNombre());
        dto.setDescripcion(procesador.getDescripcion());
        dto.setSocket(procesador.getSocket());
        dto.setCantidad(procesador.getCantidad());
        dto.setPrecioUnitario(procesador.getPrecioUnitario());
        dto.setSerial(procesador.getSerial());
        dto.setPrecioTotal(procesador.getPrecioTotal());
        return dto;
    }


    @Override
    public List<ProcesadorDTO> listarProcesadores() {
        return repositorio_procesador.findAll()
                .stream()
                .map(this::convertirProcesadorDTO)
                .toList();
    }

    @Override
    public ProcesadorDTO crearProcesador(ProcesadorDTO crearProcesadorDTO) {
        repositorio_procesador.findBySerial(crearProcesadorDTO.getSerial()).ifPresent(p -> {
            throw new EntityNotFoundException("El producto con serial: " + crearProcesadorDTO.getSerial() + " ya existe.");
        });

        Procesador productoNuevo = new Procesador();

        productoNuevo.setNombre(crearProcesadorDTO.getNombre());
        productoNuevo.setDescripcion(crearProcesadorDTO.getDescripcion());
        productoNuevo.setSerial(crearProcesadorDTO.getSerial());
        productoNuevo.setSocket(crearProcesadorDTO.getSocket());
        productoNuevo.setPrecioUnitario(crearProcesadorDTO.getPrecioUnitario());
        productoNuevo.setCantidad(crearProcesadorDTO.getCantidad());
        productoNuevo.calcularPrecioTotal();
        productoNuevo.setFechaCreacion(LocalDateTime.now());

        Procesador procesadorGuardado = repositorio_procesador.save(productoNuevo);

        return convertirProcesadorDTO(procesadorGuardado);
    }

    @Override
    public ProcesadorDTO buscarProcesadorPorNombre(String nombreProcesador) {
        return null;
    }

    @Override
    public ProcesadorDTO actualizarProcesador(Long idProcesador, ProcesadorDTO procesadorDTO) {
        return null;
    }

    @Override
    public void eliminarProcesador(Long idProcesador) {

    }
}
