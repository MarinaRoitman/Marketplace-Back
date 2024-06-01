package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
    public Optional<Categoria> getCategoryById(Long categoryId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCategoryById'");
    }

    /*public Optional<Category> getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId);
    }/* */

    public Categoria createCategoria(String nombre) throws CategoriaDuplicadaException {
        nombre = nombre.toLowerCase();
        List<Categoria> categorias = categoriaRepository.findByNombre(nombre);
        if (categorias.isEmpty())
            return categoriaRepository.save(new Categoria(nombre));
        throw new CategoriaDuplicadaException();
    }
}
