package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;
import java.sql.Blob;

import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;

public interface ProductoService {
    public List<Producto> getProductos(); 
    
    public Optional<Producto> getProductoById(Long productoId) throws ProductoInexistenteException;

    public Producto createProducto(String nombre, String description, float precio, int stock);

    public boolean agregarImagen(Long idProducto, Blob blob) throws ProductoInexistenteException;
}