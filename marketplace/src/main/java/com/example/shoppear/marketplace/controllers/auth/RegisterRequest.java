package com.example.shoppear.marketplace.controllers.auth;

// import com.uade.tpo.demo.entity.Role; // sacamos esto porque no tenemos roles

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String nombre;
    private String apellido;
    private String mail;
    private String contrasena;
    private String direccion;
    private String username;
} // estas cosas son iguales al nuevo usuario request