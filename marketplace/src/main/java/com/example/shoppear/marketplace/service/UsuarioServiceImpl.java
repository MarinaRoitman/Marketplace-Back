package com.example.shoppear.marketplace.service;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.exceptions.UsuarioExistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioLoginNoExitosoException;
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
        String contrasenaHash = DigestUtils.md5Hex(contrasena);
        if (usuarios.isEmpty())
            return usuarioRepository.save(new Usuario(nombre, apellido, mail, contrasenaHash, direccion));
        throw new UsuarioExistenteException();
    }

    @Override
    public Usuario modifyUsuario(Long id, String nombre, String apellido, String mail, String contrasena, String direccion) throws UsuarioInexistenteException {
        mail = mail.toLowerCase();
        List<Usuario> usuarios = usuarioRepository.findByMail(mail);
        String contrasenaHash = DigestUtils.md5Hex(contrasena);
        if (usuarios.size() == 1)
            return usuarioRepository.save(new Usuario(id, nombre, apellido, mail, contrasenaHash, direccion));
        throw new UsuarioInexistenteException();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) throws UsuarioInexistenteException {
        return usuarioRepository.findById(id);
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
