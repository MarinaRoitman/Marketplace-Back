package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.exceptions.UsuarioExistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioLoginNoExitosoException;
import com.example.shoppear.marketplace.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;


@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }
    
    public Usuario createUsuario(String nombre, String apellido, String mail, String contrasena, String direccion, String username) throws UsuarioExistenteException {
        mail = mail.toLowerCase();
        List<Usuario> usuarios = usuarioRepository.findByMail(mail);
        String contrasenaHash = DigestUtils.md5Hex(contrasena);
        if (usuarios.isEmpty())
            return usuarioRepository.save(new Usuario(nombre, apellido, mail, contrasenaHash, direccion, username));
        throw new UsuarioExistenteException();
    }
        
    @Override
    public Usuario modifyUsuario(Long id, String nombre, String apellido, String mail, String contrasena, String direccion, String username) throws UsuarioInexistenteException {
        mail = mail.toLowerCase();
        List<Usuario> usuarios = usuarioRepository.findByMail(mail);
        
        if (usuarios.size() == 1)
            return usuarioRepository.save(new Usuario(id, nombre, apellido, mail, passwordEncoder.encode(contrasena), direccion, username));
        throw new UsuarioInexistenteException();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) throws UsuarioInexistenteException {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> getUsuarioByMail(String mail) throws UsuarioInexistenteException {
        return usuarioRepository.findByMail(mail);
    }

    @Override
    public Long loginUsuario(String mail, String contrasena) throws UsuarioLoginNoExitosoException {
        mail = mail.toLowerCase();
        List<Usuario> usuarios = usuarioRepository.findByMail(mail);

        if (usuarios.isEmpty()) {
            throw new UsuarioLoginNoExitosoException();
        }

        Usuario usuario = usuarios.get(0);
        String contrasenaHash = DigestUtils.md5Hex(contrasena);
        if (usuario.getContrasena().equals(contrasenaHash)) {
            return usuario.getId();
        } else {
            throw new UsuarioLoginNoExitosoException();
        }
    }
}