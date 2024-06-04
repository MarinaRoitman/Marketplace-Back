package com.example.shoppear.marketplace.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.MediosDePago;

import com.example.shoppear.marketplace.repository.MediosDePagoRepository;

@Service
public class MediosDePagoServiceImpl implements MediosDePagoService {

    @Autowired
    private MediosDePagoRepository medioDePagoRepository;

    @Override
    public MediosDePago createMedioDePago(String tipo, String numero) {

        return medioDePagoRepository.save(new MediosDePago(tipo, numero));

    }

}
