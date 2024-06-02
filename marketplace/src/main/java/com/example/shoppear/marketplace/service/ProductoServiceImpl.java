package com.example.shoppear.marketplace.service;

import java.sql.Blob;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoNoSePudoCrearException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CategoriaService categoriaService;


    public List<Producto> getProductosActivos() {
        return productoRepository.findByActivoTrue();
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
    public Producto createProducto(String nombre, String descripcion, float precio, Blob img, int stock, Long idCategoria, float descuento, Long idUsuario) throws ProductoNoSePudoCrearException {
        Optional<Usuario> usuario;
        Optional<Categoria> categoria;
        try {
            usuario = usuarioService.getUsuarioById(idUsuario);
            categoria = categoriaService.getCategoriaById(idCategoria);

            return productoRepository.save(new Producto(nombre, descripcion, precio, img, stock, categoria.get(), descuento, usuario.get()));

        } catch (UsuarioInexistenteException e) {
            throw new ProductoNoSePudoCrearException();
        }catch (CategoriaInexistenteException e2) {
            throw new ProductoNoSePudoCrearException();
        }
    }

    /*
        public Categoria createCategoria(String nombre) throws CategoriaDuplicadaException {
        nombre = nombre.toLowerCase();
        List<Categoria> categorias = categoriaRepository.findByNombre(nombre);
        if (categorias.isEmpty())
            return categoriaRepository.save(new Categoria(nombre));
        throw new CategoriaDuplicadaException();
    }
     */

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

    @Override
    public Long deleteProducto(Long idProducto) throws ProductoInexistenteException {
        Optional<Producto> prod = productoRepository.findById(idProducto);
        if(prod.isPresent()){
            prod.get().setActivo(false);
            productoRepository.save(prod.get());
            return prod.get().getId();
        }else{
            throw new ProductoInexistenteException();
        }
    }
}