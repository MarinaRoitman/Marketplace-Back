package com.example.shoppear.marketplace.entity.dto;

import java.sql.Blob;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private float precio;
    private Blob img;
    private int stock;
    private Long idCategoria;
    private float descuento;
    private String creadorUsername;
    private boolean activo;
}