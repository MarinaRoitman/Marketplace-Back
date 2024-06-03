package com.example.shoppear.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.entity.Usuario;

@Repository
public interface OrdenRepository extends JpaRepository<Orden, Long> {
    Optional<Orden> findById(Long id);
    List<Orden> findByUsuario(Optional<Usuario> user);
}
