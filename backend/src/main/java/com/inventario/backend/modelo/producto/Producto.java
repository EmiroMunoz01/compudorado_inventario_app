package com.inventario.backend.modelo.producto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public abstract class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(nullable = false)
    protected String nombre;

    @Column(nullable = false)
    protected String descripcion;

    @Column(nullable = false, unique = true)
    protected String serial;

    @Column(name = "precio_compra", nullable = false)
    protected Long precioCompra;

    @Column(name = "cantidad", nullable = false)
    protected Long cantidad;

    @Column(name = "precio_total", nullable = false)
    protected Long precioTotal;

    @CreationTimestamp
    @Column(updatable = false)
    protected LocalDateTime fechaCreacion;

    public void calcularPrecioTotal() {
        this.precioTotal = this.precioCompra * this.cantidad;
    }
}


