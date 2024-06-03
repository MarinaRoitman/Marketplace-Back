package com.example.shoppear.marketplace.entity.dto;
import lombok.Data;
import java.sql.Blob;

@Data
public class ModificarProductoRequest {
    private Long id;
    private String nombre;
    private String descripcion;
    private float precio;
    private Blob img;
    private int stock;
    private Long idCategoria;
}
