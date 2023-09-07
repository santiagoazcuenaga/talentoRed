/*
 * 
 */
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
 * @author lukaku
 */
@Controller
@RequestMapping("/orden")
public class OrdenControlador {
    //Debe utilizarse las funciones de crearOrden, finalizar y cancelar desde 
    //el ServicioOrden. No olvidar, completar la lógica.

    @Autowired
    private ServicioOrden ordenservicio;
    //NO SE USA
    @GetMapping("/contrata")
    public String contrateServicio() {
        return "contratalo";
    }
    //Cliente contrata a prestador
   // @PreAuthorize("hasAnyRol('ROLE_CLIENTE')")
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
    //El boton del servicio finalizado con exito
    
    @PostMapping("/pagalo")
    public String finalizarServicio(@RequestParam String id, @RequestParam int cal, String comentario) {

        ordenservicio.finalizarOrden(id, cal, comentario);
        
        return "pagado";//reemplazar
    }
    //El cliente cancela la orden
    @GetMapping("/cancelar/{id}")
    public String cancelarServicio(@PathVariable String id) {
        try {
            ordenservicio.cancelarOrden(id);
            return "redirect:/mi_perfil/{id}";
        } catch (Exception e) {
        }
        return "cancelado";//reemplazar
    }
    //El Proveedor debe aceptar o rechazar la orden
    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR')")
    @GetMapping("/aceptada/{id}")
    public String aceptaSolicitud(@PathVariable String id){
        ordenservicio.aceptarORechazar(id, EstadoSolicitud.ACEPTADA);
        return "respuesta";//modificar retornar al perfil del proveedor
    }
    @PreAuthorize("hasAnyRole('ROLE_PROVEEDOR')")
    @GetMapping("/rechazada/{id}")
    public String rechazaSolicitud(@PathVariable String id){
        ordenservicio.aceptarORechazar(id, EstadoSolicitud.RECHAZADA);
        return "respuesta";//modificar retornar al perfil del proveedor
    }
        
    @GetMapping("/listar")
    public String listarOrdenes() {
        return "listado.html";
    }
}
