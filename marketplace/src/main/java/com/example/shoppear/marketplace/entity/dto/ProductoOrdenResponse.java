package com.example.shoppear.marketplace.entity.dto;
import java.sql.Blob;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductoOrdenResponse {
    private Long id;
    private String nombre;
    private String descripcion;
    private float precio;
    private Blob img;
    private float descuento;
    private String creadorUsername;
    private int cantidad;
}
