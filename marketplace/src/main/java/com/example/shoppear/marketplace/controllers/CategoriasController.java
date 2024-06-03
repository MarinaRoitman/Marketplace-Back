package com.example.shoppear.marketplace.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.entity.dto.CategoriaRequest;
import com.example.shoppear.marketplace.exceptions.CategoriaDuplicadaException;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;
import com.example.shoppear.marketplace.service.CategoriaService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("categorias")
public class CategoriasController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<Categoria>> getCategorias() {
        return ResponseEntity.ok(categoriaService.getCategorias());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Optional<Categoria>> getCategoryById(@PathVariable Long categoryId) throws CategoriaInexistenteException {
        return ResponseEntity.ok(categoriaService.getCategoriaById(categoryId));
    }

    @PostMapping
    public ResponseEntity<Object> createCategoria(@RequestBody CategoriaRequest categoriaRequest)
            throws CategoriaDuplicadaException {
        Categoria result = categoriaService.createCategoria(categoriaRequest.getNombre());
        return ResponseEntity.created(URI.create("/categorias/" + result.getId())).body(result);
    }
}
