package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;


import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.exceptions.CategoriaDuplicadaException;

public interface CategoriaService {
    public List<Categoria> getCategorias();

    public Optional<Categoria> getCategoryById(Long categoryId);

    public Categoria createCategoria(String description) throws CategoriaDuplicadaException;
}