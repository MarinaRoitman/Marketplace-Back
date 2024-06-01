package com.example.shoppear.marketplace.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class Usuario {

    public Usuario() {
    }

    public Usuario(String nombre, String apellido, String mail, String contrasena, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.mail = mail;
        this.contrasena = contrasena;
        this.direccion = direccion;
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

    /*@OneToMany(mappedBy = "usuario")
    private List<Producto> productos;*/
}