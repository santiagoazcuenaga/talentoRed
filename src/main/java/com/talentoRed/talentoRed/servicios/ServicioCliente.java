/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.servicios;

import com.talentoRed.talentoRed.entidades.Cliente;
import com.talentoRed.talentoRed.entidades.Imagen;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Barrio;
import com.talentoRed.talentoRed.enums.Rol;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.repositorios.repositorioUsuario;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author usuario
 */
@Service
public class ServicioCliente extends servicioUsuario{
    
    @Autowired
    private repositorioUsuario repositorioUsuario;
    @Autowired
    private ServicioImagen servicioImagen;
    
    @Transactional
    public void crearCliente(MultipartFile archivo, String nombre, String email, String password,String password2, Barrio barrio, String manzana, int casa) throws MyException {
        validar(nombre, email, password,password2);
        Cliente usuario = new Cliente();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
       // usuario.setDireccion(direccion);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.cambiarEstado(false);
        usuario.setRol(Rol.CLIENTE);
        Imagen imagen = servicioImagen.guardar(archivo);
        usuario.setImagen(imagen);
        usuario.setManzana(manzana);
        usuario.setBarrio(barrio);
        usuario.setCasa(casa);
        repositorioUsuario.save(usuario);

    }
    private void validar(String nombre, String email, String password,String password2) throws MyException {
        if (email.isEmpty() || email == null) {
            throw new MyException("el email no puede ser nulo"); //
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("el nombre no puede estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() < 8) {
            throw new MyException("la contraseña debe tener mas de 8 caracteres");
        }
        if(!password.equals(password2)){
           throw new MyException("Las contraseñas no coinciden.");
       }
    }
    
}
