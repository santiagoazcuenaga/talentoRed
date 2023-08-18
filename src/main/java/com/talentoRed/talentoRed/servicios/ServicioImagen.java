/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.servicios;

import com.talentoRed.talentoRed.entidades.Imagen;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.repositorios.RepositorioImagen;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Kidver
 */
@Service
public class ServicioImagen {
    
    @Autowired
 private RepositorioImagen imagenRepositorio;


 public Imagen guardar(MultipartFile archivo) throws MyException{
     if (archivo != null) {
         try {
            Imagen imagen = new Imagen();

            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setContenido(archivo.getBytes());
            return imagenRepositorio.save(imagen);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }

     }

  return null;
 }
 
 public Imagen actualizar(String idImagen, MultipartFile archivo) throws MyException{

      if (archivo != null) {
         try {
            Imagen imagen = new Imagen();
             if (idImagen != null) {
              Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
                 if (respuesta.isPresent()) {
                     imagen = respuesta.get();
                 }
             }
            imagen.setMime(archivo.getContentType());
            imagen.setNombre(archivo.getName());
            imagen.setContenido(archivo.getBytes());
            return imagenRepositorio.save(imagen);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }

     }

  return null;
 }


}//The end
