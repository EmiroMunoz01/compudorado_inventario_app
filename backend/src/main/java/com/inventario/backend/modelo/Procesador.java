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

    @Column(name = "serial", nullable = false, unique = true)
    private String serial;

    @Column(name = "precio_unitario", nullable = false)
    private Long precioUnitario;

    @Column(name = "cantidad", nullable = false)
    private Long cantidad;

    @Column(name = "precio_total", nullable = false)
    private Long precioTotal;

    @Enumerated(EnumType.STRING)
    @Column(name = "socket", nullable = false)
    private e_socket socket;

    public void calcularPrecioTotal() {
        this.precioTotal = this.precioUnitario * this.cantidad;
    }

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime fechaCreacion;

}
