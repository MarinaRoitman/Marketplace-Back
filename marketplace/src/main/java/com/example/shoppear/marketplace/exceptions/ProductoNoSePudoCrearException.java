package com.example.shoppear.marketplace.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "El producto no se pudo crear")
public class ProductoNoSePudoCrearException extends Exception {

}