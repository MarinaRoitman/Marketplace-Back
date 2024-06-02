package com.example.shoppear.marketplace.entity.dto;
import java.sql.Blob;

import com.example.shoppear.marketplace.entity.Categoria;

import lombok.Data;

@Data
public class NuevoProductoRequest {
    private String nombre;
    private String descripcion;
    private float precio;
    private Blob img;
    private int stock;
    private Long idCategoria;
    private float descuento;
    private Long idUsuario;
}
