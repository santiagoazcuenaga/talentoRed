/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.talentoRed.talentoRed.repositorios;

import com.talentoRed.talentoRed.entidades.OrdenDeServicio;
import com.talentoRed.talentoRed.entidades.Proveedor;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author Kidver
 */
public interface RepositorioOrden extends JpaRepository<OrdenDeServicio, String>{
    
//    @Query("SELECT a FROM OrdenServicio a WHERE a.proveedor_id = :proveedor_id")
//    public List<OrdenDeServicio> listarOrdenenesProveedor(@Param("proveedor_id") String proveedor );
//     
 //   @Query("SELECT a FROM OrdenDeServicio a WHERE a.proveedor = :")
  //  public List<OrdenDeServicio> 

    
}