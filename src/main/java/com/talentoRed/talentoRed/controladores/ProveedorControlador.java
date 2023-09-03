/*
 * 
 */
package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.entidades.Proveedor;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Disponibilidad;
import com.talentoRed.talentoRed.enums.MetodoPago;
import com.talentoRed.talentoRed.enums.TipoServicio;
import com.talentoRed.talentoRed.servicios.ServicioProveedor;
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
    public String registrarProveedor(MultipartFile archivo, TipoServicio tipoServicio, @RequestParam String nombre, @RequestParam String email,
            @RequestParam String password, @RequestParam String password2,
            String telefono, boolean tieneMatricula, String matricula, String descripcion, Disponibilidad disponibilidad,
            MetodoPago metodoPago, MultipartFile portada) {
        try {
            serPro.crearProveedor(archivo, tipoServicio, nombre, email, password, password2,telefono, tieneMatricula, matricula, descripcion,
                    disponibilidad, metodoPago, portada);
            return "redirect:/";
            //agregar el modelo con mensaje exito
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "registroPro.html";
        }
    }
    

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR')")
    @GetMapping("/mi_perfil/{id}")
    public String perfil(@PathVariable String id, ModelMap modelo) {

        Proveedor proveedor = serPro.getOne(id);
        modelo.put("user", proveedor);

        return "proveedorPerfil.html";
    }

    @GetMapping("/editar_perfil/{id}")
    public String editar_perfil(@PathVariable String id, ModelMap modelo) {

        Proveedor proveedor = serPro.getOne(id);
        modelo.put("user", proveedor);

        return "actualizarCliente.html";
    }
    
    
  //controlador para vista de proveedores x Guille
    
  /*  @GetMapping("/proveedores")
    public String Proveedores() {
        return "proveedores.html";
    }
    
    //controlador para vista de proveedores x Servicio
    
    @GetMapping("/ordenados")
    public String ordenarProveedores(ModelMap model, HttpSession session) {
        try {
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
        model.addAttribute("usuario", logueado); // Cambio a addAttribute

        List<Proveedor> usuarios = serPro.obtenerProveedoresOrdenados();
        model.put("usuarios", usuarios);
       return "ordenarProveedores.html";// 
        } catch (Exception e) {
            
            return "index.html";
        }
        
        
    }

}//The end
