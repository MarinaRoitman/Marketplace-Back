package com.example.shoppear.marketplace.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.MediosDePago;
import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.entity.OrdenProducto;
import com.example.shoppear.marketplace.entity.Producto;
import com.example.shoppear.marketplace.repository.MediosDePagoRepository;
import com.example.shoppear.marketplace.repository.OrdenProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class OrdenProductoServiceImpl implements OrdenProductoService {


    @Autowired
    private OrdenProductoRepository ordenProductoRepository;

    @Override
    @Transactional // es esto creo
    public OrdenProducto createOrdenProducto(Orden orden, Producto producto, int cantidad) {

        return ordenProductoRepository.save(new OrdenProducto(orden, producto, cantidad));
    }

}
