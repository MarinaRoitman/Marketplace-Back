package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.exceptions.CategoriaDuplicadaException;
import com.example.shoppear.marketplace.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> getCategoriaById(Long categoriaId) {
        return categoriaRepository.findById(categoriaId);
    }

    public Categoria createCategoria(String nombre) throws CategoriaDuplicadaException {
        nombre = nombre.toLowerCase();
        List<Categoria> categorias = categoriaRepository.findByNombre(nombre);
        if (categorias.isEmpty())
            return categoriaRepository.save(new Categoria(nombre));
        throw new CategoriaDuplicadaException();
    }
}
