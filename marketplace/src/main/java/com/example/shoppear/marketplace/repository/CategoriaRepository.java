package com.example.shoppear.marketplace.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.shoppear.marketplace.entity.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    //@Query(value = "select c from categoria c where c.nombre = ?")
    List<Categoria> findByNombre(String nombre);

    //List<Categoria> findAll();

}
