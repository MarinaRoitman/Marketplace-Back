package com.example.shoppear.marketplace.entity;

import java.util.Optional;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "ordenProducto")
public class OrdenProducto {

    public OrdenProducto() {}

    public OrdenProducto(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioPagado = producto.getPrecio();
        this.dctoAplicado = producto.getDescuento();
    }

    public OrdenProducto(Orden orden, Producto producto, int cantidad) {
        this.orden = orden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioPagado = producto.getPrecio();
        this.dctoAplicado = producto.getDescuento();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idOrden", referencedColumnName = "id")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    private Producto producto;

    @Column(name = "cantidad")
    private int cantidad;

    @Column(name = "precioPagado")
    private float precioPagado;

    @Column(name = "dctoAplicado")
    private float dctoAplicado;
}