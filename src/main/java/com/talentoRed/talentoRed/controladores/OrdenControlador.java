package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.EstadoSolicitud;
import com.talentoRed.talentoRed.servicios.ServicioOrden;
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
 * @author lukaku,kidver y EL GOAT
 */
@Controller
@RequestMapping("/orden")
public class OrdenControlador {
    // Debe utilizarse las funciones de crearOrden, finalizar y cancelar desde
    // el ServicioOrden. No olvidar, completar la lÃ³gica.

    @Autowired
    private ServicioOrden ordenservicio;

    // NO SE USA
    @GetMapping("/contrata")
    public String contrateServicio() {
        return "contratalo";
    }

    // Cliente contrata a prestador
    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR', 'ROLE_CLIENTE', 'ROLE_ADMIN')")
    @GetMapping("/contratar/{id}")
    public String contratarServicio(ModelMap modelo, HttpSession session, @PathVariable String id) {

        try {
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            modelo.put("user", logueado);
            ordenservicio.crearOrden(logueado.getId(), id);
            return "redirect:/inicio";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/proveedor/ordenados";
        }

    }
    // El boton del servicio finalizado con exito
    @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
    @PostMapping("/comentario/{id}")
    public String comentarServicio(@PathVariable String id,HttpSession session,Integer estrellas,String comentario) {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
        ordenservicio.comentarioYCalificacion(id, estrellas, comentario);

        return "redirect:/mi_perfil/" + user.getId();// reemplazar
    }

    // El cliente cancela la orden
    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR', 'ROLE_CLIENTE','ROLE_ADMIN')")
    @GetMapping("/cancelar/{id}")
    public String cancelarServicio(@PathVariable String id, HttpSession session) {
        Usuario user = (Usuario) session.getAttribute("usuariosession");
        try {

            ordenservicio.aceptarORechazar(id, EstadoSolicitud.CANCELADA);

            if (user.getRol().toString().equals("CLIENTE")) {
                return "redirect:/mi_perfil/" + user.getId();
            }
            return "redirect:/proveedor/mi_perfil/" + user.getId();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "redirect:/proveedor/mi_perfil/" + user.getId();
        }

    }

    // El Proveedor debe aceptar o rechazar la orden
    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR','ROLE_ADMIN')")
    @GetMapping("/aceptada/{id}")
    public String aceptaSolicitud(@PathVariable String id, HttpSession session) {
        ordenservicio.aceptarORechazar(id, EstadoSolicitud.ACEPTADA);
        Usuario user = (Usuario) session.getAttribute("usuariosession");
        return "redirect:/proveedor/mi_perfil/" + user.getId();// modificar retornar al perfil del proveedor
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR','ROLE_ADMIN')")
    @GetMapping("/rechazada/{id}")
    public String rechazaSolicitud(@PathVariable String id, HttpSession session) {
        ordenservicio.aceptarORechazar(id, EstadoSolicitud.RECHAZADA);
        Usuario user = (Usuario) session.getAttribute("usuariosession");
        return "redirect:/proveedor/mi_perfil/" + user.getId();
    }

    @GetMapping("/listar")
    public String listarOrdenes() {
        return "listado.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR', 'ROLE_CLIENTE','ROLE_ADMIN')")
    @GetMapping("/finalizar/{id}")
    public String finalizarServicioByProveedor(@PathVariable String id, HttpSession session) {

        ordenservicio.aceptarORechazar(id, EstadoSolicitud.FINALIZADA);
        Usuario user = (Usuario) session.getAttribute("usuariosession");
       if (user.getRol().toString().equals("CLIENTE")) {
            return "redirect:/mi_perfil/" + user.getId();
        }
        return "redirect:/proveedor/mi_perfil/" + user.getId();

    }
    
    
}
