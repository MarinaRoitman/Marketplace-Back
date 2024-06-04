package com.example.shoppear.marketplace.entity.dto;

import java.util.List;

import com.example.shoppear.marketplace.entity.Producto;

import lombok.Data;

@Data
public class ProductoOrdenRequest {
    private Long idProd;
    private Integer cantidad;
}
