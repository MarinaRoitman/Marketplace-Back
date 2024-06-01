package com.example.shoppear.marketplace.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "ordenProducto")
public class OrdenProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idOrden", referencedColumnName = "id")
    private Orden orden;

    /*@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idProducto", referencedColumnName = "id")
    private Producto producto;*/

    @Column(name = "cantidad")
    private int cantidad;

}