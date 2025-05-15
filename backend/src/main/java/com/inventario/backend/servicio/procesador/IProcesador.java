package com.inventario.backend.servicio.procesador;

import com.inventario.backend.dto.ProcesadorDTO;
import com.inventario.backend.modelo.Procesador;

import java.util.List;

public interface IProcesador {

    List<ProcesadorDTO> listarProcesadores();

    public ProcesadorDTO crearProcesador(ProcesadorDTO procesadorDTO);

    List<ProcesadorDTO> buscarProcesadorPorNombre(String nombreProcesador);

    List<ProcesadorDTO> buscarProcesadorPorNombreContains(String nombreProcesador);

    public ProcesadorDTO buscarProcesadorPorSerial(String serialProcesador);

    public ProcesadorDTO actualizarProcesador(String serialProcesador, ProcesadorDTO procesadorDTO);

    public void eliminarProcesador(String serialProcesador);

}
