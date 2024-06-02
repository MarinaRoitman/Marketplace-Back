package com.example.shoppear.marketplace.entity.dto;

import java.sql.Blob;

import com.example.shoppear.marketplace.entity.Categoria;

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
    private Categoria categoria;
    private float descuento;
    private String creadorUsername;
    private boolean activo;
}