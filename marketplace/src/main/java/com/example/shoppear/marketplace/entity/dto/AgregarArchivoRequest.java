package com.example.shoppear.marketplace.entity.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class AgregarArchivoRequest {
    private Long idProducto;
    private String name;
    private MultipartFile file;

    public MultipartFile getFile(){
        return file;
    }
}
