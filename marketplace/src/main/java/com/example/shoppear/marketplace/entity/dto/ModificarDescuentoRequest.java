package com.example.shoppear.marketplace.entity.dto;
import lombok.Data;

@Data
public class ModificarDescuentoRequest {
    private Long id;
    private float precio;
    private float descuento;
}
