package com.example.shoppear.marketplace.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.exceptions.CategoriaDuplicadaException;
import com.example.shoppear.marketplace.exceptions.UsuarioExistenteException;
import com.example.shoppear.marketplace.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> getUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario createUsuario(String nombre, String apellido, String mail, String contrasena, String direccion) throws UsuarioExistenteException {
        mail = mail.toLowerCase();
        List<Usuario> usuarios = usuarioRepository.findByMail(mail);
        String contrasenaHash = BCrypt.hashpw(contrasena, BCrypt.gensalt());
        if (usuarios.isEmpty())
            return usuarioRepository.save(new Usuario(nombre, apellido, mail, contrasenaHash, direccion));
        throw new UsuarioExistenteException();
    }

    /*@Override
    public Optional<Usuario> getUsuarioById(Long usuarioId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUsuarioById'");
    }*/

    /*public Usuario createCategoria(String nombre, String apellido, String mail, String contrasena, String direccion) {
        nombre = nombre.toLowerCase();
        List<Usuario> categorias = usuarioRepository.findByNombre(nombre);
        if (categorias.isEmpty())
            return usuarioRepository.save(new Categoria(nombre, apellido, mail, contrasena, direccion));
        throw new CategoriaDuplicadaException();
    }*/
}
