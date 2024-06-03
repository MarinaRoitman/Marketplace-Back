package com.example.shoppear.marketplace.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioOrdenResponse {
    private Long id;
    private String usernamePedido;
    private String nombrePedido;
    private String apellidoPedido;
}
