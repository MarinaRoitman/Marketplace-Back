package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;

import com.example.shoppear.marketplace.entity.Categoria;
import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.exceptions.CategoriaInexistenteException;
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;

public interface OrdenesService {
    public List<Orden> getOrdenes();

    public List<Orden> getOrdenesByIdUsuario(Long usuarioId) throws UsuarioInexistenteException, ListadoVacioException;
}
