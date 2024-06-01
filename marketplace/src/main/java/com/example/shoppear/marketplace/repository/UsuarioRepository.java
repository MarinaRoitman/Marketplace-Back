package com.example.shoppear.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppear.marketplace.entity.Usuario;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {
    //List<Usuario> findById(String Id);
    List<Usuario> findByMail(String mail);
}
