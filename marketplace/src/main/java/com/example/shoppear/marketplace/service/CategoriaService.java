package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;


import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.exceptions.CategoriaDuplicadaException;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;

public interface CategoriaService {
    public List<Categoria> getCategorias();

    public Optional<Categoria> getCategoriaById(Long categoriaId) throws CategoriaInexistenteException;

    public Categoria createCategoria(String description) throws CategoriaDuplicadaException;
}