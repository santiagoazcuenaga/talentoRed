/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repositorios;

import entidades.OrdenDeServicio;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Kidver
 */
public interface RepositorioOrden extends JpaRepository<OrdenDeServicio, String>{
    
    @Query("SELECT * FROM OrdenServicio a WHERE a.proveedor = :proveedor")
     public List<OrdenDeServicio> listarOrdenenesProveedor(@Param("proveedor") String proveedor );
    
}
