/*
 * 
 */
package com.talentoRed.talentoRed.servicios;


import com.talentoRed.talentoRed.entidades.OrdenDeServicio;
import com.talentoRed.talentoRed.entidades.Proveedor;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.repositorios.RepositorioOrden;
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
    
    @Transactional
    public void crearOrden(String idCliente, String idProvee, Boolean estadoServicio, int calificacion, String comentario){
        //instaciar al cliente y al prestador
        Usuario usuario = buscarUsuario(idCliente);
        Proveedor provee = buscarProvee(idProvee);
        
        //crea la Orden
        OrdenDeServicio orden = new OrdenDeServicio();
        orden.setComentario(comentario);
        orden.setCalificacion(calificacion);
        orden.setEstadoServicio(estadoServicio);//está en proceso
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
    public void finalizarOrden(String id, int calificacion, String comentario){
        Optional<OrdenDeServicio> orden = repOrden.findById(id);
        if(orden.isPresent()){
            OrdenDeServicio ordena = repOrden.getOne(id);
            ordena.setCalificacion(calificacion);
            ordena.setComentario(comentario);
            ordena.setEstadoServicio(false);
            repOrden.save(ordena);
            //hay que ver cómo hacemos que la orden se reemplace o que se duplique.
        }
    }
    //listar las ordenes
    public List<OrdenDeServicio> listarOrdenes(){
        List<OrdenDeServicio> ordenes = repOrden.findAll();
        return ordenes;   
    }
    
    
}