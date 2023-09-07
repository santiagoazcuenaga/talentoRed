package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.entidades.OrdenDeServicio;
import com.talentoRed.talentoRed.entidades.Proveedor;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Disponibilidad;
import com.talentoRed.talentoRed.enums.MetodoPago;
import com.talentoRed.talentoRed.enums.TipoServicio;
import com.talentoRed.talentoRed.servicios.ServicioOrden;
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

    @Autowired
    private ServicioOrden serOrden;
    
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
            serPro.crearProveedor(archivo, tipoServicio, nombre, email, password, password2, telefono, tieneMatricula, matricula, descripcion,
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
        List<OrdenDeServicio> ordenes = serOrden.listarOrdenProveedor(id);
        modelo.put("ordenes", ordenes);
        Double estrellas = serOrden.calcularCalificacion(ordenes);
        modelo.put("estrellas", estrellas);
        
        modelo.put("user", proveedor);
        return "proveedorPerfil.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR')")
    @GetMapping("/editar_perfil/{id}")
    public String editar_perfil(@PathVariable String id, ModelMap modelo) {

        Proveedor proveedor = serPro.getOne(id);
        modelo.put("user", proveedor);

        return "actualizarProveedor.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR')")
    @PostMapping("/editar_perfil/{id}")
    public String editar_perfil(MultipartFile archivo, @PathVariable String id, TipoServicio tipoServicio, @RequestParam String nombre, @RequestParam String email,
            String telefono, boolean tieneMatricula, String matricula, String descripcion, Disponibilidad disponibilidad,
            MetodoPago metodoPago, MultipartFile portada) {
        
        try {            
            serPro.modificarProveedor(archivo, id, tipoServicio, nombre, email, telefono, tieneMatricula, matricula, descripcion, disponibilidad, metodoPago, portada);
            return "redirect:/proveedor/mi_perfil/"+id;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "actualizarProveedor.html";
        }
    }

    //controlador para vista de proveedores x Servicio
    
    @GetMapping("/ordenados")
    public String ordenarProveedores(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerProveedoresOrdenados();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/albañil")
    public String ordenarAlbañil(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerAlbañiles();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }
   
    @GetMapping("/carpintero")
    public String ordenarCarpintero(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerCarpinteros();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }
    
    @GetMapping("/electricista")
    public String ordenarElectricista(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerElectricistas();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }
  
    @GetMapping("/gas")
    public String ordenarGas(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerGas();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }

    @GetMapping("/jardinero")
    public String ordenarJardineros(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerJardineros();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }
   
    @GetMapping("/mecanico")
    public String ordenarMecanicos(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerMecanicos();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }
   
    @GetMapping("/plomero")
    public String ordenarPlomero(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerPlomeros();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }
   
    @GetMapping("/tecnico")
    public String ordenarTecnicos(ModelMap model, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            model.put("user", logueado);
            List<Proveedor> usuarios = serPro.obtenerTecnicos();
            model.addAttribute("usuarios", usuarios);
            return "ordenarProveedores.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "index.html";
        }
    }
    @GetMapping("/proveedorinfo/{id}")
    public String proveedorinfo(@PathVariable String id, ModelMap modelo){
          Proveedor proveedor = serPro.getOne(id);
        modelo.put("proveedor", proveedor);

        return "proveedorclasificacion.html";

    }
    
    
    

}//The end