package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;

import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.exceptions.UsuarioExistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioLoginNoExitosoException;

public interface UsuarioService {
    public List<Usuario> getUsuarios();

    public Usuario createUsuario(String nombre, String apellido, String mail, String contrasena, String direccion) throws UsuarioExistenteException;

    public Usuario modifyUsuario(Long id, String nombre, String apellido, String mail, String contrasena, String direccion) throws UsuarioInexistenteException;

    public Optional <Usuario> getUsuarioById(Long id) throws UsuarioInexistenteException;

    public Long loginUsuario (String mail, String contrasena) throws UsuarioLoginNoExitosoException;
}