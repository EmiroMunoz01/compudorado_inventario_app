package com.inventario.backend.modelo.producto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "entidad_memoria_ram")

public class MemoriaRAM extends Producto{



    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

}
