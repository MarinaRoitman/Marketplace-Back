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
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
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

    @Override
    public Producto modifyProducto(Long productoId, String nombre, String description, float precio, Blob img, int stock, Long idCategoria) throws ProductoInexistenteException, CategoriaInexistenteException {
        Optional<Producto> prods = productoRepository.findById(productoId);
        if (prods.isPresent()){
            Optional<Categoria> categoria = categoriaService.getCategoriaById(idCategoria);

            prods.get().setNombre(nombre);
            prods.get().setDescripcion(description);
            prods.get().setPrecio(precio);
            prods.get().setImg(img);
            prods.get().setStock(stock);
            prods.get().setCategoria(categoria.get());
            return productoRepository.save(prods.get());

        }else{
            throw new ProductoInexistenteException();
        }
    }

    @Override
    public Producto modifyDescuento(Long productoId,float descuento)throws ProductoInexistenteException {
        Optional<Producto> prods = productoRepository.findById(productoId);
        if (prods.isPresent()){
            prods.get().setDescuento(descuento);
            return productoRepository.save(prods.get());

        }else{
            throw new ProductoInexistenteException();
        }
    }

    @Override
    public List<Producto> getProductosByUsuario(Long idUsuario) throws ListadoVacioException, UsuarioInexistenteException {
        Optional<Usuario> user = usuarioService.getUsuarioById(idUsuario);
        List<Producto> productos = productoRepository.findByUsuario(user);
        if(productos.size() > 0){
            return productos;
        }else{
            throw new ListadoVacioException();
        }
    }

    @Override
    public List<Producto> getProductosByCategoria(Long idCategoria) throws ListadoVacioException, CategoriaInexistenteException {
        Optional<Categoria> cat = categoriaService.getCategoriaById(idCategoria);
        List<Producto> productos = productoRepository.findByCategoria(cat);
        if(productos.size() > 0){
            return productos;
        }else{
            throw new ListadoVacioException();
        }
    }
}