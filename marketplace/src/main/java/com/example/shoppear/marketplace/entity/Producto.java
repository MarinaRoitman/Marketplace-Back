package com.example.shoppear.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.sql.Blob;

@Data
@Entity
public class Producto {
    
    public Producto() {
    }

    public Producto(String nombre, String descripcion, float precio, Blob img, int stock, Categoria categoria, float descuento, Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.img = img;
        this.stock = stock;
        this.categoria = categoria;
        this.descuento = descuento;
        this.usuario = usuario;
        this.activo = true;
    }

    public void setImg(Blob img){
        this.img = img;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String descripcion;

    @Column
    private float precio;

    @Column
    private Blob img;

    @Column
    private int stock;

    @ManyToOne
    @JoinColumn(name = "idCategoria", referencedColumnName = "id")
    private Categoria categoria;

    @Column
    private float descuento;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column
    private boolean activo;
}