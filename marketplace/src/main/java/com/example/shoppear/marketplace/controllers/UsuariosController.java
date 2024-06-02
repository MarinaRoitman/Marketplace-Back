package com.example.shoppear.marketplace.controllers;

import java.net.URI;
import java.util.List;
import java.util.Locale.Category;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.entity.dto.LoginUsuarioRequest;
import com.example.shoppear.marketplace.entity.dto.ModificarUsuarioRequest;
import com.example.shoppear.marketplace.entity.dto.NuevoUsuarioRequest;
import com.example.shoppear.marketplace.exceptions.UsuarioExistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioLoginNoExitosoException;
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

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long usuarioId) throws UsuarioInexistenteException {
        Optional<Usuario> result = usuarioService.getUsuarioById(usuarioId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Object> createUsuario(@RequestBody NuevoUsuarioRequest usuarioRequest)
            throws UsuarioExistenteException {
        Usuario result = usuarioService.createUsuario(usuarioRequest.getNombre(), usuarioRequest.getApellido(), usuarioRequest.getMail(), usuarioRequest.getContrasena(), usuarioRequest.getDireccion());
        return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUsuario(@RequestBody LoginUsuarioRequest usuarioRequest)
            throws UsuarioLoginNoExitosoException {
        Long result = usuarioService.loginUsuario(usuarioRequest.getMail(), usuarioRequest.getContrasena());
        return ResponseEntity.created(URI.create("/usuarios/login/" + result)).body(result);
    }

    @PutMapping
    public ResponseEntity<Object> modifyUsuario(@RequestBody ModificarUsuarioRequest usuarioRequest) throws UsuarioInexistenteException{
        Usuario result = usuarioService.modifyUsuario(usuarioRequest.getId(), usuarioRequest.getNombre(), usuarioRequest.getApellido(), usuarioRequest.getMail(), usuarioRequest.getContrasena(), usuarioRequest.getDireccion());
        return ResponseEntity.created(URI.create("/usuarios/" + result.getId())).body(result);
    }
}
