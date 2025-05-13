package com.inventario.backend.servicio.procesador;

import com.inventario.backend.dto.ProcesadorDTO;
import com.inventario.backend.modelo.Procesador;

import java.util.List;

public interface IProcesador {

    List<ProcesadorDTO> listarProcesadores();

    public ProcesadorDTO crearProcesador(ProcesadorDTO procesadorDTO);

    public ProcesadorDTO buscarProcesadorPorNombre(String nombreProcesador);

    public ProcesadorDTO actualizarProcesador(Long idProcesador, ProcesadorDTO procesadorDTO);

    public void eliminarProcesador(Long idProcesador);

}
