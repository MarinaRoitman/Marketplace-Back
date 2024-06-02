package com.example.shoppear.marketplace.entity.dto;

import lombok.Data;

@Data
public class NuevoUsuarioRequest {
    private String nombre;
    private String apellido;
    private String mail;
    private String contrasena;
    private String direccion;
}
