/*
 * 
 */
package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.enums.EstadoSolicitud;
import com.talentoRed.talentoRed.servicios.ServicioOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
    
    @GetMapping("/contrata")
    public String contrateServicio(){
        return "contratalo.html";
    }
    @PostMapping("/contratar")
    public String contratarServicio(@RequestParam String idCliente, @RequestParam String idProvee, EstadoSolicitud estadoServicio, int calificacion, String comentario){
        
        ordenservicio.crearOrden(idCliente, idProvee,estadoServicio,calificacion,comentario);
        
        return "contratalo.html";//reemplazar
    }
    
    

    
    @PostMapping("/pagalo")
    public String finalizarServicio(@RequestParam String id, @RequestParam int cal, String comentario){
        
        ordenservicio.finalizarOrden(id, cal, comentario);
        
        return "pagado.html";//reemplazar
    }
    
    @PostMapping("/cancelar")
    public String cancelarServicio(@RequestParam String id){
        
        ordenservicio.cancelarOrden(id);
        
        return "cancelado.html";//reemplazar
    }
    @GetMapping("/listar")
    public String listarOrdenes(){
        return "listado.html";
    }
}
