/*package com.example.shoppear.marketplace.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.service.CategoriaService;
import com.example.shoppear.marketplace.service.ProductoService;

@RestController
@RequestMapping("productos")
public class ProductosController {

    @Autowired
    private ProductoService productoService;


    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
            return ResponseEntity.ok(productoService.getProductos());
    }
}
*/