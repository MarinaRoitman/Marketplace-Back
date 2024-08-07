package com.example.shoppear.marketplace.controllers;

import java.io.IOException;
import java.net.URI;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.entity.dto.AgregarArchivoRequest;
import com.example.shoppear.marketplace.entity.dto.ImageResponse;
import com.example.shoppear.marketplace.entity.dto.ModificarDescuentoRequest;
import com.example.shoppear.marketplace.entity.dto.ModificarProductoRequest;
import com.example.shoppear.marketplace.entity.dto.NuevoProductoRequest;
import com.example.shoppear.marketplace.entity.dto.ProductoResponse;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoNoImgException;
import com.example.shoppear.marketplace.exceptions.ProductoNoSePudoCrearException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.service.ProductoService;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;


@RestController
@RequestMapping("/auth/productos")
public class ProductosController {

    @Autowired
    private ProductoService productoService;

    @CrossOrigin (origins = "http://localhost:5173")
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> getProductosActivos() throws SQLException {
        List<Producto> prods = productoService.getProductosActivos();
        List<ProductoResponse> prodsDto = new ArrayList<ProductoResponse>();
        for(Producto p : prods){
            String img = null;
            if (p.getImg() != null) {
                img = Base64.getEncoder().encodeToString(p.getImg().getBytes(1, (int) p.getImg().length()));
            }


            prodsDto.add(ProductoResponse.builder().id(p.getId()).nombre(p.getNombre()).descripcion(p.getDescripcion()).precio(p.getPrecio()).img(img).stock(p.getStock()).idCategoria(p.getCategoria().getId()).descuento(p.getDescuento()).creadorUsername(p.getUsuario().getUsername()).activo(p.isActivo()).build());
        }

        return ResponseEntity.ok().body(prodsDto);
    }

    @CrossOrigin (origins = "http://localhost:5173")
    @GetMapping("{id}")
    public ResponseEntity<ProductoResponse> getProductoById(@PathVariable Long id) throws ProductoInexistenteException, SQLException {
        Optional<Producto> productoOptional = productoService.getProductoById(id);
        Producto producto;
        String img = null;

        if(productoOptional.isPresent()){
            producto = productoOptional.get();

            if (producto.getImg() != null) {
                img = Base64.getEncoder().encodeToString(producto.getImg().getBytes(1, (int) producto.getImg().length()));
            }

        }else{
            throw new ProductoInexistenteException();
        }
        return ResponseEntity.ok().body(ProductoResponse.builder().id(producto.getId()).nombre(producto.getNombre()).descripcion(producto.getDescripcion()).precio(producto.getPrecio()).img(img).stock(producto.getStock()).idCategoria(producto.getCategoria().getId()).descuento(producto.getDescuento()).creadorUsername(producto.getUsuario().getUsername()).activo(producto.isActivo()).build());
    }

    // hacer que este metodo sea privado
    @CrossOrigin (origins = "http://localhost:5173")
    @PostMapping("/img")
    public String addImagePost(AgregarArchivoRequest request) throws IOException, SerialException, SQLException, ProductoInexistenteException {
        byte[] bytes = request.getFile().getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        boolean creado = productoService.agregarImagen(request.getIdProducto(), blob);
        return (creado ? "created" : "error");
    }

    @CrossOrigin (origins = "http://localhost:5173")
    @GetMapping("/img/{id}")
    public ResponseEntity<ImageResponse> displayImage(@PathVariable Long id) throws IOException, SQLException, ProductoNoImgException, ProductoInexistenteException {
        Optional<Producto> producto = productoService.getProductoById(id);
        if (producto.isPresent()) {
            Blob image = producto.get().getImg();
            if (image != null) {
                String encodedString = Base64.getEncoder().encodeToString(image.getBytes(1, (int) image.length()));
                return ResponseEntity.ok().body(ImageResponse.builder().file(encodedString).id(id).build());
            } else {
                throw new ProductoNoImgException();
            }
        } else {
            throw new ProductoInexistenteException();
        }
    }


    // hacer que este metodo sea privado
    @CrossOrigin (origins = "http://localhost:5173")
    @DeleteMapping
    public ResponseEntity<Object> deleteProducto(@RequestParam(name = "idProducto") Long idProducto) throws ProductoInexistenteException{
        //Hace baja logica del producto, no lo borra realmente
        Long result = productoService.deleteProducto(idProducto);
        return ResponseEntity.created(URI.create("/productos/" + result)).body(result);
    }

    // hacer que este metodo sea privado
    @CrossOrigin (origins = "http://localhost:5173")
    @PostMapping
    public ResponseEntity<Object> createProducto(@RequestBody NuevoProductoRequest productoRequest) throws ProductoNoSePudoCrearException, SQLException{
        Blob img = null;
        if (productoRequest.getImg() != null) {
            byte[] imgBytes = Base64.getDecoder().decode(productoRequest.getImg());
            try {
                img = new javax.sql.rowset.serial.SerialBlob(imgBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Producto p = productoService.createProducto(productoRequest.getNombre(), productoRequest.getDescripcion(), productoRequest.getPrecio(), img, productoRequest.getStock(), productoRequest.getIdCategoria(), productoRequest.getDescuento(), productoRequest.getIdUsuario());
        
        return ResponseEntity.created(URI.create("/productos/" + p.getId())).body(ProductoResponse.builder()
                                                                                                    .id(p.getId())
                                                                                                    .nombre(p.getNombre())
                                                                                                    .descripcion(p.getDescripcion())
                                                                                                    .precio(p.getPrecio())
                                                                                                    .img(productoRequest.getImg())
                                                                                                    .stock(p.getStock())
                                                                                                    .idCategoria(p.getCategoria()
                                                                                                    .getId())
                                                                                                    .descuento(p.getDescuento())
                                                                                                    .creadorUsername(p.getUsuario().getUsername()).activo(p.isActivo()).build());
    }

    // hacer que este metodo sea privado
    @CrossOrigin (origins = "http://localhost:5173")
    @PutMapping("/{id}")
    public ResponseEntity<Object> modifyProducto(@RequestBody ModificarProductoRequest productoRequest) throws ProductoInexistenteException, CategoriaInexistenteException, SQLException{
        Blob img = null;
        if (productoRequest.getImg() != null) {
            byte[] imgBytes = Base64.getDecoder().decode(productoRequest.getImg());
            try {
                img = new javax.sql.rowset.serial.SerialBlob(imgBytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Producto result = productoService.modifyProducto(productoRequest.getId(), productoRequest.getNombre(), productoRequest.getDescripcion(), productoRequest.getPrecio(), img, productoRequest.getStock(), productoRequest.getIdCategoria());

        return ResponseEntity.created(URI.create("/productos/" + result.getId())).body(ProductoResponse.builder().id(result.getId()).nombre(result.getNombre()).descripcion(result.getDescripcion()).precio(result.getPrecio()).img(productoRequest.getImg()).stock(result.getStock()).idCategoria(result.getCategoria().getId()).descuento(result.getDescuento()).creadorUsername(result.getUsuario().getUsername()).activo(result.isActivo()).build());
    }

    // hacer que este metodo sea privado
    @CrossOrigin (origins = "http://localhost:5173")
    @PutMapping("/descuento/{id}")
    public ResponseEntity<Object> modifyDescuento(@RequestBody ModificarDescuentoRequest productoRequest) throws ProductoInexistenteException, SQLException{
        Producto result = productoService.modifyDescuento(productoRequest.getId(), productoRequest.getDescuento());
        String img = null;
        if (result.getImg() != null) {
            img = Base64.getEncoder().encodeToString(result.getImg().getBytes(1, (int) result.getImg().length()));
        }

        return ResponseEntity.created(URI.create("/productos/" + result.getId())).body(ProductoResponse.builder().id(result.getId()).nombre(result.getNombre()).descripcion(result.getDescripcion()).precio(result.getPrecio()).img(img).stock(result.getStock()).idCategoria(result.getCategoria().getId()).descuento(result.getDescuento()).creadorUsername(result.getUsuario().getUsername()).activo(result.isActivo()).build());
    }

    @CrossOrigin (origins = "http://localhost:5173")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ProductoResponse>> getProductosPorUsuario(@PathVariable Long idUsuario) throws UsuarioInexistenteException, SQLException {
        List<Producto> prods;
        try {
            prods = productoService.getProductosByUsuario(idUsuario);
        } catch (ListadoVacioException e) {
            return ResponseEntity.ok().body(new ArrayList<ProductoResponse>());
        }

        List<ProductoResponse> prodsDto = new ArrayList<ProductoResponse>();

        

        for(Producto p : prods){
            String img = null;
            if (p.getImg() != null) {
                img = Base64.getEncoder().encodeToString(p.getImg().getBytes(1, (int) p.getImg().length()));
            }


            prodsDto.add(ProductoResponse.builder().id(p.getId()).nombre(p.getNombre()).descripcion(p.getDescripcion()).precio(p.getPrecio()).img(img).stock(p.getStock()).idCategoria(p.getCategoria().getId()).descuento(p.getDescuento()).creadorUsername(p.getUsuario().getUsername()).activo(p.isActivo()).build());
        }
        return ResponseEntity.ok().body(prodsDto);
    }

    @CrossOrigin (origins = "http://localhost:5173")
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponse>> getProductosPorCategoria(@PathVariable Long idCategoria) throws ListadoVacioException, CategoriaInexistenteException, SQLException {
        List<Producto> prods = productoService.getProductosByCategoria(idCategoria);
        List<ProductoResponse> prodsDto = new ArrayList<ProductoResponse>();
        for(Producto p : prods){
            String img = null;
            if (p.getImg() != null) {
                img = Base64.getEncoder().encodeToString(p.getImg().getBytes(1, (int) p.getImg().length()));
            }


            prodsDto.add(ProductoResponse.builder().id(p.getId()).nombre(p.getNombre()).descripcion(p.getDescripcion()).precio(p.getPrecio()).img(img).stock(p.getStock()).idCategoria(p.getCategoria().getId()).descuento(p.getDescuento()).creadorUsername(p.getUsuario().getUsername()).activo(p.isActivo()).build());
        }
        return ResponseEntity.ok().body(prodsDto);
    }
}