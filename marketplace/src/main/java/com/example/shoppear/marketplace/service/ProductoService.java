package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;
import java.sql.Blob;

import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoNoSePudoCrearException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;

public interface ProductoService {
    public List<Producto> getProductosActivos(); 
    
    public Optional<Producto> getProductoById(Long productoId) throws ProductoInexistenteException;

    public Producto createProducto(String nombre, String description, float precio, Blob img, int stock, Long idCategoria, float descuento, Long idUsuario) throws ProductoNoSePudoCrearException;

    public boolean agregarImagen(Long idProducto, Blob blob) throws ProductoInexistenteException;

    public Long deleteProducto(Long idProducto) throws ProductoInexistenteException;

    public Producto modifyProducto(Long productoId, String nombre, String description, float precio, Blob img, int stock, Long idCategoria) throws ProductoInexistenteException, CategoriaInexistenteException;

    public Producto modifyDescuento(Long productoId, float descuento) throws ProductoInexistenteException;

    public List<Producto> getProductosByUsuario(Long idUsuario) throws ListadoVacioException, UsuarioInexistenteException; 

    public List<Producto> getProductosByCategoria(Long idCategoria) throws ListadoVacioException, CategoriaInexistenteException; 

}