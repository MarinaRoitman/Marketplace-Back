package com.example.shoppear.marketplace.service;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;
import com.example.shoppear.marketplace.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long productoId) throws ProductoInexistenteException {
        Optional<Producto> producto = productoRepository.findById(productoId);
        if(producto.isPresent()){
            return producto;
        }else{
            throw new ProductoInexistenteException();
        }
    }

    @Override
    public Producto createProducto(String nombre, String description, float precio, int stock) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createProducto'");
    }

    @Override
    public boolean agregarImagen(Long idProducto, Blob blob) throws ProductoInexistenteException {
        Optional<Producto> producto = productoRepository.findById(idProducto);

        if(producto.isPresent()){
            producto.get().setImg(blob);
            Producto prod = productoRepository.save(producto.get());
            return (prod != null);
        }else{
            throw new ProductoInexistenteException();
        }
    }

}