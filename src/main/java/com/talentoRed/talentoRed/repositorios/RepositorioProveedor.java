/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.talentoRed.talentoRed.repositorios;

import com.talentoRed.talentoRed.entidades.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kidver
 */
@Repository
public interface RepositorioProveedor extends JpaRepository<Proveedor, String>{

    @Query("SELECT p FROM Usuario p WHERE p.nombre = :email ")
    public Proveedor buscarProveedorPorEmail(@Param("email") String nombre);

}
