package com.example.shoppear.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Usuario {

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String mail, String contrasena, String direccion, String username) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.username = username;
    }

    public Usuario(Long id, String nombre, String apellido, String mail, String contrasena, String direccion, String username) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contrasena = contrasena;
        this.direccion = direccion;
        this.username = username;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String mail;

    @Column
    private String contrasena;

    @Column
    private String direccion;

    @Column
    private String username;

    /*@OneToMany(mappedBy = "usuario")
    private List<Producto> productos;*/
}