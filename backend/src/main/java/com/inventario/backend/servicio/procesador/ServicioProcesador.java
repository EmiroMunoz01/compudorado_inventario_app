package com.inventario.backend.servicio.procesador;

import com.inventario.backend.dto.ProcesadorDTO;
import com.inventario.backend.modelo.Procesador;
import com.inventario.backend.repositorio.Repositorio_procesador;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


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
        dto.setFechaCreacion(procesador.getFechaCreacion());
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
    public List<ProcesadorDTO> buscarProcesadorPorNombre(String nombreProcesador) {

        List<Procesador> procesadores = repositorio_procesador.findByNombre(nombreProcesador);

        if (procesadores.isEmpty()) {
            throw new EntityNotFoundException("El producto con el nombre: " + nombreProcesador + " no existe.");
        }

        // Aquí se convierte el objeto Procesador a ProcesadorDTO antes de retornarlo
        return procesadores.stream()
                .map(this::convertirProcesadorDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProcesadorDTO> buscarProcesadorPorNombreContains(String nombreProcesador) {

        // Eliminar espacios extras y convertir a minúsculas para búsqueda insensible a mayúsculas
        String nombreBusqueda = nombreProcesador.trim().toLowerCase();

        // Obtener todos los procesadores y filtrar
        List<Procesador> procesadores = repositorio_procesador.findAll().stream()
                .filter(p -> p.getNombre().toLowerCase().contains(nombreBusqueda))
                .toList();

        if (procesadores.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron procesadores con: " + nombreProcesador);
        }

        // Convertir todos los resultados a DTO
        return procesadores.stream()
                .map(this::convertirProcesadorDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProcesadorDTO buscarProcesadorPorSerial(String serialProcesador) {

        Procesador procesador = repositorio_procesador.findBySerial(serialProcesador)
                .orElseThrow(() -> new EntityNotFoundException("El producto con el serial: " + serialProcesador + " no existe."));

        // Aquí se convierte el objeto Procesador a ProcesadorDTO antes de retornarlo
        return convertirProcesadorDTO(procesador);
    }


    @Override
    public ProcesadorDTO actualizarProcesador(String serialProcesador, ProcesadorDTO procesadorDTO) {

        Procesador procesador = repositorio_procesador.findBySerial(serialProcesador)
                .orElseThrow(() -> new EntityNotFoundException("El producto con el serial: " + serialProcesador + " no existe."));

        procesador.setNombre(procesadorDTO.getNombre());
        procesador.setDescripcion(procesadorDTO.getDescripcion());
        procesador.setSerial(procesadorDTO.getSerial());
        procesador.setSocket(procesadorDTO.getSocket());
        procesador.setPrecioUnitario(procesadorDTO.getPrecioUnitario());
        procesador.setCantidad(procesadorDTO.getCantidad());
        procesador.calcularPrecioTotal();

        repositorio_procesador.save(procesador);

        return convertirProcesadorDTO(procesador);
    }

    @Transactional
    @Override
    public void eliminarProcesador(String serialProcesador) {
        Procesador procesador = repositorio_procesador.findBySerial(serialProcesador)
                .orElseThrow(() -> new EntityNotFoundException("El producto con el serial: " + serialProcesador + " no existe."));

        repositorio_procesador.deleteProcesadorsBySerial(serialProcesador);

    }
}
