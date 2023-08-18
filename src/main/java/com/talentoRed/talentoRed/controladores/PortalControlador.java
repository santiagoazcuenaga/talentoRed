/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.entidades.Usuario;
import javax.xml.bind.ValidationException;
import com.talentoRed.talentoRed.myExceptions.MyException;

import com.talentoRed.talentoRed.servicios.servicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author usuario
 */
@Controller
@RequestMapping("/")

public class PortalControlador {

    @Autowired
    servicioUsuario serusa = new servicioUsuario();

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/registrar")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registro")
    public String registrar(MultipartFile archivo, @RequestParam String nombre, @RequestParam String email, String password, String password2) {
        try {
            serusa.crearUsuario(archivo, nombre, email, password,password2);
            // Registro exitoso, redirigir a la página de inicio de sesión
            return "redirect:/";
        } catch (MyException e) {
            // Error durante el registro, mostrar mensaje de error en la página de registro
            // Puedes agregar el mensaje de error a través del Model y mostrarlo en la plantilla
            // o redirigir a una página de error personalizada
            System.out.println(e.getMessage());
            return "registro.html";
        }
    }
<<<<<<< HEAD
    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            Usuario usuario = serusa.obtenerUsuarioActual();
            if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
                // El usuario no ha establecido su información personal
                return "redirect:/completar-informacion-usuario";
            } else {
                // El usuario ya ha establecido su información personal
                return "redirect:/";
            }
        }
        return "login.html";
    }
=======
<<<<<<< HEAD
   
=======
    
    
    

>>>>>>> 4ec87624bfbf88a391c30617be4d128feaf3cad0
>>>>>>> 506e5d8645f719f512143be7d45366899f8ffd10
}
