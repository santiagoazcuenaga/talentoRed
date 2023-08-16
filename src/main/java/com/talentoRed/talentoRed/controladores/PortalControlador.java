/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.controladores;

import javax.xml.bind.ValidationException;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
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
    ServicioUsuario serusa = new ServicioUsuario();
    
    
    @GetMapping("/")
    public String index(){
        return "index.html";
    }
       @GetMapping("/registrar")
   public String registrar(){
       return "registro.html";
   }
    @PostMapping("/registro") // Cambiado a POST en lugar de GET
    public String registrar(@RequestParam String nombre,@RequestParam String email,@RequestParam String password,String direccion) {
        try {
            serusa.crearUsuario(nombre, email, password, direccion);
            // Registro exitoso, redirigir a la página de inicio de sesión
            return "redirect:/";
        } catch (MyException e) {
            // Error durante el registro, mostrar mensaje de error en la página de registro
            // Puedes agregar el mensaje de error a través del Model y mostrarlo en la plantilla
            // o redirigir a una página de error personalizada
            return "registro.html";
        }
    }
    
    
    
}
