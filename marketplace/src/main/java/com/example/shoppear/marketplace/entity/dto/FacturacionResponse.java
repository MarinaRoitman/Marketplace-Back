package com.example.shoppear.marketplace.entity.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FacturacionResponse {
    private String direccionFactura;
    private String ultDigitos;
    private String tipoTarjeta;
}
