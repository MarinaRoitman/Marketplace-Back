package com.example.shoppear.marketplace.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class MediosDePago {

    public MediosDePago(){}
    
    public MediosDePago(String tipoPago, String numeroTarjeta) {
        this.tipo = tipoPago;
        this.numero = numeroTarjeta;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String tipo;

    @Column
    private String numero;
}
