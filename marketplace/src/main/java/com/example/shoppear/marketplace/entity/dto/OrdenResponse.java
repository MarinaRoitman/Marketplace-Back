package com.example.shoppear.marketplace.entity.dto;

import java.sql.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdenResponse {
    private Long id;
    private UsuarioOrdenResponse comprador;
    private List<ProductoOrdenResponse> productos;
    private Date fecha;
}
