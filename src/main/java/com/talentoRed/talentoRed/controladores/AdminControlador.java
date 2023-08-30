/*
 * 
 */
package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Rol;
import com.talentoRed.talentoRed.servicios.ServicioUsuario;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lukaku
 */
@Controller
@RequestMapping("/admin")
public class AdminControlador {

    @Autowired
    ServicioUsuario usuarioservicio;

    @GetMapping("/dashboard")
    public String panelAdministrativo(ModelMap modelo, HttpSession session) {
        Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        modelo.addAttribute("usuario", logueado); // Cambio a addAttribute

        List<Usuario> usuarios = usuarioservicio.listarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "panelAdmin"; // No es necesario agregar ".html"
    }
        
    @PostMapping("/modificarRol/{id}")
    public String cambiarRol(@PathVariable String id, @RequestParam("rol") Rol rol) {
        usuarioservicio.cambiarRol(id, rol);
        return "redirect:/admin/dashboard";
    }
    
    @PostMapping("/cambiarEstado/{id}")
    public String cambiarEstado(@PathVariable String id){
        usuarioservicio.cambiarEstado(id);
        return "redirect:/admin/dashboard";
    }
    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable String id){
        usuarioservicio.eliminarUsuario(id);
        return "redirect:/admin/dashboard";
    }
}
