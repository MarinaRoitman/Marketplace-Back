package com.example.shoppear.marketplace.entity.dto;
import java.sql.Blob;

import lombok.Data;

@Data
public class NuevoProductoRequest {
    private String nombre;
    private String descripcion;
    private float precio;
    private String img;
    private int stock;
    private Long idCategoria;
    private float descuento;
    private Long idUsuario;
}
