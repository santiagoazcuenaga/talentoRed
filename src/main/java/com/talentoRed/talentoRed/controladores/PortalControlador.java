/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Barrio;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.servicios.ServicioCliente;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author usuario
 */

@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ServicioCliente serCli;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/registrarCliente")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registroCliente")
    public String registrar(MultipartFile archivo, @RequestParam String nombre, @RequestParam String email,
            String password, String password2, Barrio barrio, String manzana, int casa) {
        try {
            serCli.crearCliente(archivo, nombre, email, password, password2, barrio, manzana, casa);            
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
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            System.out.println("Usuario o contraseña invalida");
            modelo.put("error", "Informacion invalida");
        }
        return "login.html";
    }


    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR')")

    @GetMapping("/inicio")
    public String inicio(HttpSession session) {
        try {
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            if (logueado.getRol().toString().equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            }
            return "inicio.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "index.html";

        }
    }
}
