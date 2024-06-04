package com.example.shoppear.marketplace.entity.dto;
import java.sql.Date;
import java.util.List;

import com.example.shoppear.marketplace.entity.OrdenProducto;
import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.entity.Usuario;

import lombok.Data;

@Data
public class OrdenRequest {
    private Long idUsuario;
    private List<ProductoOrdenRequest> detalleProds;
    private String direccionFactura;
    private String tipoPago;
    private String numeroTarjeta;
}
