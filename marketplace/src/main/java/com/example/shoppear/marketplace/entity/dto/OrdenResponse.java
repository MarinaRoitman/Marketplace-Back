package com.example.shoppear.marketplace.entity.dto;

import java.util.Date;
import java.util.List;

import com.example.shoppear.marketplace.entity.MediosDePago;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrdenResponse {
    private Long id;
    private UsuarioOrdenResponse comprador;
    private List<ProductoOrdenResponse> productos;
    private Date fecha;
    private FacturacionResponse datosFacturacion;
}
