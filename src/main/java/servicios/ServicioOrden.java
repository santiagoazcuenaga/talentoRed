/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import entidades.OrdenDeServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import repositorios.RepositorioOrden;

/**
 * @author Kidver
 */
public class ServicioOrden {

    @Autowired
    private RepositorioOrden repositorioOrden;

    @Transactional
    public void crearOrden() {

    }

    public List<OrdenDeServicio> listarUsuarios() {
        List<OrdenDeServicio> orden;
        orden = repositorioOrden.findAll();
        return orden;
    }
    
    public void cancelarOrden(){
        
    }

}//The end
