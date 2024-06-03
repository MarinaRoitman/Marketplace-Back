package com.example.shoppear.marketplace.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.entity.OrdenProducto;
import com.example.shoppear.marketplace.entity.dto.OrdenResponse;
import com.example.shoppear.marketplace.entity.dto.ProductoOrdenResponse;
import com.example.shoppear.marketplace.entity.dto.UsuarioOrdenResponse;
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
import com.example.shoppear.marketplace.exceptions.OrdenInexistenteException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.service.OrdenesService;

@RestController
@RequestMapping("ordenes")
public class OrdenesController {

    @Autowired
    private OrdenesService ordenesService;
    
    @GetMapping
    public ResponseEntity<List<OrdenResponse>> getOrdenes() {
        List<Orden> ordenes = ordenesService.getOrdenes();
        List<OrdenResponse> ordenesDto = new ArrayList<OrdenResponse>();
        for(Orden o : ordenes){
            UsuarioOrdenResponse uoResponse = UsuarioOrdenResponse.builder().id(o.getUsuario().getId()).usernamePedido(o.getUsuario().getUsername()).nombrePedido(o.getUsuario().getNombre()).apellidoPedido(o.getUsuario().getApellido()).build();
            List<ProductoOrdenResponse> prods = new ArrayList<ProductoOrdenResponse>();
            for(OrdenProducto op : o.getOrdenProducto()){
                ProductoOrdenResponse poResponse = ProductoOrdenResponse.builder().id(op.getProducto().getId()).nombre(op.getProducto().getNombre()).descripcion(op.getProducto().getDescripcion()).precio(op.getProducto().getPrecio()).img(op.getProducto().getImg()).descuento(op.getProducto().getDescuento()).creadorUsername(op.getProducto().getUsuario().getUsername()).cantidad(op.getCantidad()).build();
                prods.add(poResponse);
            }
            ordenesDto.add(OrdenResponse.builder().id(o.getId()).comprador(uoResponse).productos(prods).fecha(o.getFecha()).build());
        }

        return ResponseEntity.ok().body(ordenesDto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<OrdenResponse>> getOrdenesByIdUsuario(@PathVariable Long usuarioId) throws UsuarioInexistenteException, ListadoVacioException {
        List<Orden> ordenes = ordenesService.getOrdenesByIdUsuario(usuarioId);
        List<OrdenResponse> ordenesDto = new ArrayList<OrdenResponse>();
        for(Orden o : ordenes){
            UsuarioOrdenResponse uoResponse = UsuarioOrdenResponse.builder().id(o.getUsuario().getId()).usernamePedido(o.getUsuario().getUsername()).nombrePedido(o.getUsuario().getNombre()).apellidoPedido(o.getUsuario().getApellido()).build();
            List<ProductoOrdenResponse> prods = new ArrayList<ProductoOrdenResponse>();
            for(OrdenProducto op : o.getOrdenProducto()){
                ProductoOrdenResponse poResponse = ProductoOrdenResponse.builder().id(op.getProducto().getId()).nombre(op.getProducto().getNombre()).descripcion(op.getProducto().getDescripcion()).precio(op.getProducto().getPrecio()).img(op.getProducto().getImg()).descuento(op.getProducto().getDescuento()).creadorUsername(op.getProducto().getUsuario().getUsername()).cantidad(op.getCantidad()).build();
                prods.add(poResponse);
            }
            ordenesDto.add(OrdenResponse.builder().id(o.getId()).comprador(uoResponse).productos(prods).fecha(o.getFecha()).build());
        }
        return ResponseEntity.ok().body(ordenesDto);
    }

    /*
    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Long usuarioId) throws UsuarioInexistenteException {
        Optional<Usuario> result = usuarioService.getUsuarioById(usuarioId);
        if (result.isPresent())
            return ResponseEntity.ok(result.get());

        return ResponseEntity.noContent().build();
    }
     */
}
