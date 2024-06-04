package com.example.shoppear.marketplace.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "Orden")
public class Orden {

    public Orden(){}

    public Orden(Usuario usuario, List<OrdenProducto> ordenProductos, Date fecha, MediosDePago medioDePago, String direccionFactura) {
        this.usuario = usuario;
        this.ordenProducto = ordenProductos; 
        this.fecha = fecha;
        this.medioDePago = medioDePago;
        this.direccionFactura = direccionFactura;
    }

    public Orden(Usuario usuario, Date fecha, MediosDePago medio, String direccionFactura) {
        this.usuario = usuario;
        this.fecha = fecha;
        this.medioDePago = medio;
        this.direccionFactura = direccionFactura;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idUsuario", referencedColumnName = "id")
    private Usuario usuario;

    @OneToMany(mappedBy="orden")
    private List<OrdenProducto> ordenProducto;


    @Column(name = "fecha")
    private Date fecha;


    @OneToOne
    @JoinColumn(name = "idMedioDePago", referencedColumnName = "id")
    private MediosDePago medioDePago;

    @Column
    private String direccionFactura;
}