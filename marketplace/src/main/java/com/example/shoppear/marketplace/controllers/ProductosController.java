package com.example.shoppear.marketplace.controllers;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.entity.dto.AgregarArchivoRequest;
import com.example.shoppear.marketplace.entity.dto.ImageResponse;
import com.example.shoppear.marketplace.exceptions.ProductoInexistenteException;
import com.example.shoppear.marketplace.exceptions.ProductoNoImgException;
import com.example.shoppear.marketplace.service.ProductoService;
import java.sql.Blob;
import java.util.Base64;


@RestController
@RequestMapping("productos")
public class ProductosController {

    @Autowired
    private ProductoService productoService;


    @GetMapping
    public ResponseEntity<List<Producto>> getProductos() {
            return ResponseEntity.ok(productoService.getProductos());
    }

    @PostMapping("/img")
    public String addImagePost(AgregarArchivoRequest request) throws IOException, SerialException, SQLException, ProductoInexistenteException {
        byte[] bytes = request.getFile().getBytes();
        Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
        boolean creado = productoService.agregarImagen(request.getIdProducto(), blob);
        return (creado ? "created" : "error");
    }

    @CrossOrigin
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
}