package com.example.shoppear.marketplace.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.entity.dto.UsuarioRequest;
import com.example.shoppear.marketplace.exceptions.UsuarioExistenteException;
import com.example.shoppear.marketplace.service.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuariosController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        return ResponseEntity.ok(usuarioService.getUsuarios());
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@RequestBody UsuarioRequest usuarioRequest)
            throws UsuarioExistenteException {
        Usuario result = usuarioService.createUsuario(usuarioRequest.getNombre(), usuarioRequest.getApellido(), usuarioRequest.getMail(), usuarioRequest.getContrasena(), usuarioRequest.getDireccion());
        return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
    }
}
