/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.servicios.ServicioProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author Kidver
 */

@Controller
@RequestMapping("/proveedor")
public class ProveedorControlador {
    
    @Autowired
    private ServicioProveedor serPro;
    
    @GetMapping("/registrar")
    public String registrarProveedor() {
        return "registroPro.html";
    }
    
    @PostMapping("/registro")
    public String registrarProveedor(MultipartFile archivo,@RequestParam String nombre, @RequestParam String email, 
            @RequestParam String password,@RequestParam String password2, 
            int nroContacto, boolean tieneMatricula, int matricula, String descripcion){
        try {            
            serPro.crearProveedor(archivo, nombre, email, password, password2, nroContacto, tieneMatricula, matricula, descripcion);  
            return "redirect:/";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "registroPro.html";
        }
        
        
    }


}//The end
