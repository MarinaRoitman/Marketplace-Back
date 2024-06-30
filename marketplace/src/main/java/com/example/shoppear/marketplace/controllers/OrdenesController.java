package com.example.shoppear.marketplace.controllers;

import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.entity.OrdenProducto;
import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.entity.dto.FacturacionResponse;
import com.example.shoppear.marketplace.entity.dto.NuevoProductoRequest;
import com.example.shoppear.marketplace.entity.dto.OrdenRequest;
import com.example.shoppear.marketplace.entity.dto.OrdenResponse;
import com.example.shoppear.marketplace.entity.dto.ProductoOrdenResponse;
import com.example.shoppear.marketplace.entity.dto.ProductoResponse;
import com.example.shoppear.marketplace.entity.dto.UsuarioOrdenResponse;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
import com.example.shoppear.marketplace.exceptions.OrdenInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoNoSePudoCrearException;
import com.example.shoppear.marketplace.exceptions.SinStockException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.service.OrdenesService;

@RestController
@RequestMapping("/auth/ordenes")
public class OrdenesController {

    @Autowired
    private OrdenesService ordenesService;
    
    @CrossOrigin (origins = "http://localhost:5173")
    @GetMapping
    public ResponseEntity<List<OrdenResponse>> getOrdenes() throws SQLException {
        List<Orden> ordenes = ordenesService.getOrdenes();
        List<OrdenResponse> ordenesDto = new ArrayList<OrdenResponse>();
        for(Orden o : ordenes){
            UsuarioOrdenResponse uoResponse = UsuarioOrdenResponse.builder().id(o.getUsuario().getId()).usernamePedido(o.getUsuario().getUsername()).nombrePedido(o.getUsuario().getNombre()).apellidoPedido(o.getUsuario().getApellido()).build();
            FacturacionResponse facturacionResponse = FacturacionResponse.builder().direccionFactura(o.getDireccionFactura()).ultDigitos("*".concat(o.getMedioDePago().getNumero().substring(o.getMedioDePago().getNumero().length()-4))).tipoTarjeta(o.getMedioDePago().getTipo()).build();

            List<ProductoOrdenResponse> prods = new ArrayList<ProductoOrdenResponse>();
            for(OrdenProducto op : o.getOrdenProducto()){
                String img = null;
                if (op.getProducto().getImg() != null) {
                    img = Base64.getEncoder().encodeToString(op.getProducto().getImg().getBytes(1, (int) op.getProducto().getImg().length()));
                }
                ProductoOrdenResponse poResponse = ProductoOrdenResponse.builder().id(op.getProducto().getId()).nombre(op.getProducto().getNombre()).descripcion(op.getProducto().getDescripcion()).precio(op.getPrecioPagado()).img(img).descuento(op.getDctoAplicado()).creadorUsername(op.getProducto().getUsuario().getUsername()).cantidad(op.getCantidad()).build();
                prods.add(poResponse);
            }
            ordenesDto.add(OrdenResponse.builder().id(o.getId()).comprador(uoResponse).productos(prods).fecha(o.getFecha()).datosFacturacion(facturacionResponse).build());
        }

        return ResponseEntity.ok().body(ordenesDto);
    }

    @CrossOrigin (origins = "http://localhost:5173")
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<OrdenResponse>> getOrdenesByIdUsuario(@PathVariable Long usuarioId) throws UsuarioInexistenteException, ListadoVacioException, SQLException {
        List<Orden> ordenes = ordenesService.getOrdenesByIdUsuario(usuarioId);
        List<OrdenResponse> ordenesDto = new ArrayList<OrdenResponse>();
        for(Orden o : ordenes){
            UsuarioOrdenResponse uoResponse = UsuarioOrdenResponse.builder().id(o.getUsuario().getId()).usernamePedido(o.getUsuario().getUsername()).nombrePedido(o.getUsuario().getNombre()).apellidoPedido(o.getUsuario().getApellido()).build();
            FacturacionResponse facturacionResponse = FacturacionResponse.builder().direccionFactura(o.getDireccionFactura()).ultDigitos("*".concat(o.getMedioDePago().getNumero().substring(o.getMedioDePago().getNumero().length()-4))).tipoTarjeta(o.getMedioDePago().getTipo()).build();
            
            List<ProductoOrdenResponse> prods = new ArrayList<ProductoOrdenResponse>();
            for(OrdenProducto op : o.getOrdenProducto()){
                String img = null;
                if (op.getProducto().getImg() != null) {
                    img = Base64.getEncoder().encodeToString(op.getProducto().getImg().getBytes(1, (int) op.getProducto().getImg().length()));
                }
                ProductoOrdenResponse poResponse = ProductoOrdenResponse.builder().id(op.getProducto().getId()).nombre(op.getProducto().getNombre()).descripcion(op.getProducto().getDescripcion()).precio(op.getPrecioPagado()).img(img).descuento(op.getDctoAplicado()).creadorUsername(op.getProducto().getUsuario().getUsername()).cantidad(op.getCantidad()).build();
                prods.add(poResponse);
            }
            ordenesDto.add(OrdenResponse.builder().id(o.getId()).comprador(uoResponse).productos(prods).fecha(o.getFecha()).datosFacturacion(facturacionResponse).build());
        }
        return ResponseEntity.ok().body(ordenesDto);
    }

    @CrossOrigin (origins = "http://localhost:5173")
    @GetMapping("{id}")
    public ResponseEntity<OrdenResponse> getOrdenesById(@PathVariable Long id) throws OrdenInexistenteException, ListadoVacioException, SQLException {
        Optional<Orden> o = ordenesService.getOrdenesById(id);
        UsuarioOrdenResponse uoResponse = UsuarioOrdenResponse.builder().id(o.get().getUsuario().getId()).usernamePedido(o.get().getUsuario().getUsername()).nombrePedido(o.get().getUsuario().getNombre()).apellidoPedido(o.get().getUsuario().getApellido()).build();
        FacturacionResponse facturacionResponse = FacturacionResponse.builder().direccionFactura(o.get().getDireccionFactura()).ultDigitos("*".concat(o.get().getMedioDePago().getNumero().substring(o.get().getMedioDePago().getNumero().length()-4))).tipoTarjeta(o.get().getMedioDePago().getTipo()).build();

        List<ProductoOrdenResponse> prods = new ArrayList<ProductoOrdenResponse>();
        for(OrdenProducto op : o.get().getOrdenProducto()){
            String img = null;
            if (op.getProducto().getImg() != null) {
                img = Base64.getEncoder().encodeToString(op.getProducto().getImg().getBytes(1, (int) op.getProducto().getImg().length()));
            }
            ProductoOrdenResponse poResponse = ProductoOrdenResponse.builder().id(op.getProducto().getId()).nombre(op.getProducto().getNombre()).descripcion(op.getProducto().getDescripcion()).precio(op.getPrecioPagado()).img(img).descuento(op.getDctoAplicado()).creadorUsername(op.getProducto().getUsuario().getUsername()).cantidad(op.getCantidad()).build();
            prods.add(poResponse);
        }
        OrdenResponse ordenDto = OrdenResponse.builder().id(o.get().getId()).comprador(uoResponse).productos(prods).fecha(o.get().getFecha()).datosFacturacion(facturacionResponse).build();
        return ResponseEntity.ok().body(ordenDto);
    }


    // hacer que este metodo sea privado
    @CrossOrigin (origins = "http://localhost:5173")
    @PostMapping
    public ResponseEntity<Object> createOrden(@RequestBody OrdenRequest ordenRequest) throws UsuarioInexistenteException, ProductoInexistenteException, CategoriaInexistenteException, SinStockException, SQLException{       
        Orden o = ordenesService.createOrden(ordenRequest.getIdUsuario(), ordenRequest.getDetalleProds(), ordenRequest.getDireccionFactura(), ordenRequest.getTipoPago(), ordenRequest.getNumeroTarjeta());
        
        UsuarioOrdenResponse uoResponse = UsuarioOrdenResponse.builder().id(o.getUsuario().getId()).usernamePedido(o.getUsuario().getUsername()).nombrePedido(o.getUsuario().getNombre()).apellidoPedido(o.getUsuario().getApellido()).build();
        FacturacionResponse facturacionResponse = FacturacionResponse.builder().direccionFactura(o.getDireccionFactura()).ultDigitos("*".concat(o.getMedioDePago().getNumero().substring(o.getMedioDePago().getNumero().length()-4))).tipoTarjeta(o.getMedioDePago().getTipo()).build();

        List<ProductoOrdenResponse> prods = new ArrayList<ProductoOrdenResponse>();
        for(OrdenProducto op : o.getOrdenProducto()){
            String img = null;
            if (op.getProducto().getImg() != null) {
                img = Base64.getEncoder().encodeToString(op.getProducto().getImg().getBytes(1, (int) op.getProducto().getImg().length()));
            }
            ProductoOrdenResponse poResponse = ProductoOrdenResponse.builder().id(op.getProducto().getId()).nombre(op.getProducto().getNombre()).descripcion(op.getProducto().getDescripcion()).precio(op.getPrecioPagado()).img(img).descuento(op.getDctoAplicado()).creadorUsername(op.getProducto().getUsuario().getUsername()).cantidad(op.getCantidad()).build();
            prods.add(poResponse);
        }
        
        OrdenResponse ordenDto = OrdenResponse.builder().id(o.getId()).comprador(uoResponse).productos(prods).fecha(o.getFecha()).datosFacturacion(facturacionResponse).build();
        return ResponseEntity.created(URI.create("/ordenes/" + o.getId())).body(ordenDto);
    }
}
