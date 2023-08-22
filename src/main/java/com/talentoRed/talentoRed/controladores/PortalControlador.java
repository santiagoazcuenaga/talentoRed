/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Barrio;
import javax.xml.bind.ValidationException;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.servicios.ServicioCliente;
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
    ServicioCliente serusa = new ServicioCliente();
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/registrarCliente")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registroCliente")
    public String registrar(MultipartFile archivo, @RequestParam String nombre, @RequestParam String email, String password, String password2, Barrio barrio, String manzana, int casa){
        try {
            serusa.crearCliente(archivo, nombre, email, password,password2, barrio, manzana, casa);
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

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.isAuthenticated() && !(auth instanceof AnonymousAuthenticationToken)) {
            Usuario usuario = serusa.obtenerUsuarioActual();
            if (usuario.getNombre() == null || usuario.getNombre().isEmpty()) {
                // El usuario no ha establecido su información personal
                return "redirect:/login";
            } else {
                // El usuario ya ha establecido su información personal
                return "redirect:/";
            }
        }
        return "login.html";
    }
    

    @GetMapping("/registrarProveedor")
    public String registrarProveedor() {
        return "registro2.html";
    }

    @PostMapping("/registroProveedor")
    public String registrarProveedor(MultipartFile archivo, @RequestParam String nombre, @RequestParam String email, String password, String password2) {
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
}
