/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.talentoRed.talentoRed.repositorios;

import com.talentoRed.talentoRed.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kidver
 */
@Repository
public interface RepositorioCliente extends JpaRepository<Cliente, String>{
    
    @Query("SELECT a FROM Usuario a WHERE a.email = :email ")
    public Cliente buscarClientePorEmail(@Param("email") String email);
}
