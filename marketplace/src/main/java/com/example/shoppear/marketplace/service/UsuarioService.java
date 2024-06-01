package com.example.shoppear.marketplace.service;

import java.util.List;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.exceptions.CategoriaDuplicadaException;
import com.example.shoppear.marketplace.exceptions.UsuarioExistenteException;

public interface UsuarioService {
    public List<Usuario> getUsuarios();

    public Usuario createUsuario(String nombre, String apellido, String mail, String contrasena, String direccion) throws UsuarioExistenteException;
}