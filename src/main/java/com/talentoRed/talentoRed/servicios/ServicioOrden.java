/*
 * 
 */
package com.talentoRed.talentoRed.servicios;


import com.talentoRed.talentoRed.entidades.OrdenDeServicio;
import com.talentoRed.talentoRed.entidades.Proveedor;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.EstadoSolicitud;
import com.talentoRed.talentoRed.repositorios.RepositorioOrden;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Kidver y lukaku
 */
@Service
public class ServicioOrden {
    
    @Autowired
    private RepositorioOrden repOrden;
    
    @Autowired
    private ServicioUsuario usuarioservicio;
    
    @Autowired
    private ServicioProveedor proveeservicio;
    
    public Usuario buscarUsuario(String id){
        return usuarioservicio.getOne(id);   
    }
    
    public Proveedor buscarProvee(String id){
        return proveeservicio.getOne(id);
    }
    
    
    //METODO EXCLUSIVO DEL CLIENTE.
    @Transactional
    public void crearOrden(String idCliente, String idProvee){
        //instaciar al cliente y al prestador
        Usuario usuario = buscarUsuario(idCliente);
        Proveedor provee = buscarProvee(idProvee);
        
        //crea la Orden
        OrdenDeServicio orden = new OrdenDeServicio();

        orden.setComentario(" ");
        orden.setCalificacion(0);
        orden.setEstadoServicio(EstadoSolicitud.PENDIENTE);//está en proceso
                                    //cuando finaliza cambia a "false"
        orden.setProveedor(provee);
        orden.setUsuario(usuario);
        //guarda y persiste
        repOrden.save(orden);
        
    }
    
    @Transactional
    public void cancelarOrden(String id){
        repOrden.deleteById(id);
    }
    
    @Transactional
    //ESTE METODO ES EXCLUSIVO DEL PROVEEDOR.
    public void aceptarORechazar(String id,EstadoSolicitud estadoServicio){
     Optional<OrdenDeServicio> respuesta = repOrden.findById(id);
     OrdenDeServicio orden = respuesta.get();
     if(respuesta.isPresent()){
    orden.setEstadoServicio(estadoServicio);
    repOrden.save(orden);
     }
        
    }
    
    
    
    @Transactional
    public void finalizarOrden(String id, int calificacion, String comentario){
        Optional<OrdenDeServicio> orden = repOrden.findById(id);
        if(orden.isPresent()){
            OrdenDeServicio ordena = repOrden.getOne(id);
            ordena.setCalificacion(calificacion);
            ordena.setComentario(comentario);
            ordena.setEstadoServicio(EstadoSolicitud.FINALIZADA);
            repOrden.save(ordena);
            //hay que ver cómo hacemos que la orden se reemplace o que se duplique.
        }
    }
    //listar las ordenes
    public List<OrdenDeServicio> listarOrdenes(){
        List<OrdenDeServicio> ordenes = repOrden.findAll();
        return ordenes;   
    }
    
     //Metodo para captar las ordenes de un proveedor
     public List<OrdenDeServicio> listarOrdenProveedor(String id){
        List<OrdenDeServicio> ordenes = repOrden.findAll();
        
        List<OrdenDeServicio> aux = new ArrayList();
         for (OrdenDeServicio orden : ordenes) {
             if(orden.getProveedor().getId().equals(id)){
                 aux.add(orden);
             }
             
         }
         return aux;   
    }
 
     //Listar las ordenes solicitadas por cliente
      public List<OrdenDeServicio> listarOrdenCliente(String id){
        List<OrdenDeServicio> ordenes = repOrden.findAll();
        
        List<OrdenDeServicio> aux = new ArrayList();
         for (OrdenDeServicio orden : ordenes) {
             if(orden.getUsuario().getId().equals(id)){
                 aux.add(orden);
             }
             
         }
         return aux;   
    }
    
}