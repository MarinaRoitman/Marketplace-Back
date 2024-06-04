package com.example.shoppear.marketplace.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.MediosDePago;
import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.entity.OrdenProducto;
import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.entity.dto.ProductoOrdenRequest;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
import com.example.shoppear.marketplace.exceptions.OrdenInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.repository.OrdenRepository;

@Service
public class OrdenesServiceImpl implements OrdenesService{

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private OrdenProductoService ordenProductoService;

    @Autowired
    private MediosDePagoService mediosDePagoService;

    @Autowired
    private UsuarioService usuarioService;

    public List<Orden> getOrdenes() {
        return ordenRepository.findAll();
    }

    @Override
    public List<Orden> getOrdenesByIdUsuario(Long usuarioId) throws UsuarioInexistenteException, ListadoVacioException {
        Optional<Usuario> user = usuarioService.getUsuarioById(usuarioId);
        List<Orden> ordenes = ordenRepository.findByUsuario(user);
        if(ordenes.size() > 0){
            return ordenes;
        }else{
            throw new ListadoVacioException();
        }
    }

    @Override
    public Optional<Orden> getOrdenesById(Long id) throws OrdenInexistenteException, ListadoVacioException {
        Optional<Orden> orden = ordenRepository.findById(id);
        if(orden.isPresent()){
            return orden;
        }else{
            throw new OrdenInexistenteException();
        }
    }

    @Override
    public Orden createOrden(Long idUsuario, List<ProductoOrdenRequest> detalleProds, String direccionFactura, String tipoPago, String numeroTarjeta) throws UsuarioInexistenteException, ProductoInexistenteException, CategoriaInexistenteException {
        Optional<Usuario> user = usuarioService.getUsuarioById(idUsuario);
        if(user.isPresent()){
                        
            MediosDePago medio = mediosDePagoService.createMedioDePago(tipoPago, numeroTarjeta);

            Orden orden = ordenRepository.save(new Orden(user.get(), new Date(), medio, direccionFactura));

            List<OrdenProducto> productosOrden = new ArrayList<OrdenProducto>();
            for(ProductoOrdenRequest opRequest: detalleProds){
                try{
                    Optional<Producto> producto = productoService.getProductoById(opRequest.getIdProd());
                
                    if(producto.isPresent()){
                        int nuevoStock = producto.get().getStock() - opRequest.getCantidad();
                        if(producto.get().getStock() > 0){
                            if(opRequest.getCantidad() > producto.get().getStock()){
                                opRequest.setCantidad(producto.get().getStock());
                                nuevoStock = 0;
                            }

                            productosOrden.add(new OrdenProducto(producto.get(), opRequest.getCantidad()));
                            ordenProductoService.createOrdenProducto(orden, producto.get(), opRequest.getCantidad());
                            productoService.modifyProducto(producto.get().getId(), producto.get().getNombre(), producto.get().getDescripcion(), producto.get().getPrecio(), producto.get().getImg(), nuevoStock, producto.get().getCategoria().getId());
                        }
                    }
                } catch(ProductoInexistenteException e){

                }
            
            }

            orden.setMedioDePago(medio);
            orden.setOrdenProducto(productosOrden);
            
            return orden;
        }else{
            throw new UsuarioInexistenteException();
        }


    }

    /*
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
     */
}
