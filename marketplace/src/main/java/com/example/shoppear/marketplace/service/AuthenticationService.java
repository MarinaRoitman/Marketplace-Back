package com.example.shoppear.marketplace.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shoppear.marketplace.controllers.auth.AuthenticationRequest;
import com.example.shoppear.marketplace.controllers.auth.AuthenticationResponse;
import com.example.shoppear.marketplace.controllers.auth.RegisterRequest;
import com.example.shoppear.marketplace.controllers.config.JwtService;
import com.example.shoppear.marketplace.entity.Usuario;
import com.example.shoppear.marketplace.entity.dto.NuevoUsuarioRequest;
import com.example.shoppear.marketplace.repository.UsuarioRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UsuarioRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;
        
        public AuthenticationResponse register(NuevoUsuarioRequest request) {
                var user = Usuario.builder()
                        .nombre(request.getNombre())
                        .apellido(request.getApellido())
                        .mail(request.getMail())
                        .contrasena(passwordEncoder.encode(request.getContrasena()))
                        .direccion(request.getDireccion())
                        .username(request.getUsername())
                        .build();

                repository.save(user);
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                        .accessToken(jwtToken)
                        .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getEmail(),
                                                request.getPassword()));
                List<Usuario> usuarios = repository.findByMail(request.getEmail());
                var user = usuarios.stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
                
                var jwtToken = jwtService.generateToken(user);
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}