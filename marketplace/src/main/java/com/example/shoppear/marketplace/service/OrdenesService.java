package com.example.shoppear.marketplace.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import com.example.shoppear.marketplace.entity.MediosDePago;
import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.entity.OrdenProducto;
import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.entity.dto.ProductoOrdenRequest;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
import com.example.shoppear.marketplace.exceptions.OrdenInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;

public interface OrdenesService {
    public List<Orden> getOrdenes();

    public List<Orden> getOrdenesByIdUsuario(Long usuarioId) throws UsuarioInexistenteException, ListadoVacioException;

    public Optional<Orden> getOrdenesById(Long id) throws OrdenInexistenteException, ListadoVacioException;
    
    public Orden createOrden(Long idUsuario, List<ProductoOrdenRequest> detalleProds, String direccionFactura, String tipoPago, String numeroTarjeta) throws UsuarioInexistenteException, ProductoInexistenteException, CategoriaInexistenteException;

}
