package com.example.shoppear.marketplace.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.entity.Usuario;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);
    List<Producto> findByActivoTrue();
    Optional<Producto> findById(Long id);
    List<Producto> findByUsuario(Optional<Usuario> user);
    List<Producto> findByCategoria(Optional<Categoria> cat);    
}