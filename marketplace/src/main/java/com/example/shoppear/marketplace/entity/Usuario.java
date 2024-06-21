package com.example.shoppear.marketplace.entity;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.lang.Collections;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@Entity
public class Usuario implements UserDetails{

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

    

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of(new SimpleGrantedAuthority(role.name()));
        //no tenemos roles, asi que devuelve una lista vacia PREGUNTAR!!1!
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return contrasena;
    }
}