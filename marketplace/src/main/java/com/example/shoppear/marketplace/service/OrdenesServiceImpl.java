package com.example.shoppear.marketplace.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.entity.Orden;
import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.exceptions.ListadoVacioException;
import com.example.shoppear.marketplace.exceptions.UsuarioInexistenteException;
import com.example.shoppear.marketplace.repository.OrdenRepository;
import com.example.shoppear.marketplace.repository.UsuarioRepository;

@Service
public class OrdenesServiceImpl implements OrdenesService{

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private UsuarioService usuarioService;

    public List<Orden> getOrdenes() {
        return ordenRepository.findAll();
    }

    @Override
    public List<Orden> getOrdenesByIdUsuario(Long usuarioId) throws UsuarioInexistenteException, ListadoVacioException {
        Optional<Usuario> user = usuarioService.getUsuarioById(usuarioId);
        List<Orden> ordenes = ordenRepository.findByUsuario(user);
        if(ordenes.size() > 0){
            return ordenes;
        }else{
            throw new ListadoVacioException();
        }
    }

}
