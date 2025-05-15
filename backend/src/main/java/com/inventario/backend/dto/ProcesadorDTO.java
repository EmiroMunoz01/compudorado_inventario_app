package com.inventario.backend.dto;

import com.inventario.backend.modelo.producto.e_socket;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProcesadorDTO {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 4, message = "El nombre debe tener al menos 4 caracteres")
    private String nombre;

    @NotBlank(message = "La descripcion no puede estar vacía")
    @Size(min = 10, message = "La descripcion debe tener al menos 10 caracteres")
    private String descripcion;

    @NotBlank(message = "El serial no puede estar vacío")
    @Size(min = 4, message = "El serial debe tener al menos 4 caracteres")
    private String serial;

    @NotNull(message = "La cantidad no puede ser 0")
    @Min(value = 1, message = "La cantidad debe tener al menos 1 dígito")
    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @NotNull(message = "El precio no puede ser 0")
    @Min(value = 1, message = "El precio debe tener al menos 6 dígitos")
    private Long precioUnitario;

    private Long precioTotal;

    private e_socket socket;

    private LocalDateTime fechaCreacion;

}
