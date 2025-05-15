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
public class MemoriaRam {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;


    @Column(name = "capacidad", nullable = false)
    private Integer capacidad;


    @Column(name = "frecuencia", nullable = false)
    private Integer frecuencia;


    @Column(name = "marca", nullable = false)
    private String marca;

    @Enumerated(EnumType.STRING)
    @Column(name = "formato", nullable = false)
    private e_formato_ram formato;

    @Enumerated(EnumType.STRING)
    @Column(name = "plataforma", nullable = false)
    private e_formato_ram_plataforma plataforma;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

}
