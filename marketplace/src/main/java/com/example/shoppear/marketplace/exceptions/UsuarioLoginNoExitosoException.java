package com.example.shoppear.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Email o contraseña incorrectos")
public class UsuarioLoginNoExitosoException extends Exception {

}