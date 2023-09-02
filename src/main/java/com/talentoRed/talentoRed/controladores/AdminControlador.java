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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
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
    public String cambiarEstado(@PathVariable String id) {
        usuarioservicio.darAlta(id);
        return "redirect:/admin/dashboard";
    }

    @PostMapping("/eliminarUsuario/{id}")
    public String eliminarUsuario(@PathVariable String id) {
        usuarioservicio.eliminarUsuario(id);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/ordenar")
    public String redireccionarAOrdenar() {
        return "redirect:/admin/ordenarUsuarios";
    }

    @GetMapping("/ordenarUsuarios")
    public String ordenarUsuarios(ModelMap modelo) {
        List<Usuario> usuarios = usuarioservicio.ordenarUsuarios();
        modelo.addAttribute("usuarios", usuarios);
        return "panelAdmin";
    }
    @GetMapping("/mostrar")
    public String redireccionarAOrdenarClientes() {
        return "redirect:/admin/mostrarClientes";
    }
    @GetMapping("/mostrarClientes")
    public String mostrarClientes(ModelMap modelo) {
        List<Usuario> usuarios = usuarioservicio.mostrarClientes();
        modelo.addAttribute("usuarios", usuarios);
        return "panelAdmin";
    }
     @GetMapping("/mostrarPro")
    public String redireccionarAOrdenarProveedores() {
        return "redirect:/admin/mostrarProveedores";
    }
    @GetMapping("/mostrarProveedores")
    public String mostrarProveedores(ModelMap modelo) {
        List<Usuario> usuarios = usuarioservicio.mostrarProveedores();
        modelo.addAttribute("usuarios", usuarios);
        return "panelAdmin";
    }
}
