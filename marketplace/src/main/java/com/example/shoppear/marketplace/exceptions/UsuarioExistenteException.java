package com.example.shoppear.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Ya hay un usuario registrado con ese email")
public class UsuarioExistenteException extends Exception {

}