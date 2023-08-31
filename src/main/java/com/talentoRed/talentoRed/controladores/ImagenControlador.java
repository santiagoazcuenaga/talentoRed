
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.controladores;


import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



public class ImagenControlador {

    @Autowired
    ServicioUsuario usuarioServicio;

    @GetMapping("/perfil/{id}")
    public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) {
        Usuario usuario = usuarioServicio.getOne(id);

        byte[] imagen = usuario.getImagen().getContenido();

        HttpHeaders headers = new HttpHeaders();  // dice que es de tipo imagen a la peticion
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
    }
}
