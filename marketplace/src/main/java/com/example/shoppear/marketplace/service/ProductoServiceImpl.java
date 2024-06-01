/*package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.repository.CategoriaRepository;
import com.example.shoppear.marketplace.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long productoId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductoById'");
    }

    @Override
    public Producto createProducto(String nombre, String description, float precio, int stock) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProducto'");
    }

}
*/