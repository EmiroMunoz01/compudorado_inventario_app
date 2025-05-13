package com.inventario.backend.modelo;

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
@Table(name = "entidad_procesador")
public class Procesador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @Column(name = "precio", nullable = false)
    private Long precio;

    @Enumerated(EnumType.STRING)
    @Column(name = "socket", nullable = false)
    private e_socket socket;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

}
