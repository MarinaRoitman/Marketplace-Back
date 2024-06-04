package com.example.shoppear.marketplace.service;

import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.entity.OrdenProducto;
import com.example.shoppear.marketplace.entity.Producto;

public interface OrdenProductoService {
    public OrdenProducto createOrdenProducto(Orden orden, Producto producto, int cantidad);
}
