/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.repositorios;

import com.talentoRed.talentoRed.entidades.Cliente;
import com.talentoRed.talentoRed.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author usuario
 */
public interface RepositorioCliente extends JpaRepository<Cliente, String> {

  @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.rol = 'CLIENTE'")
    Cliente buscarClientePorEmail(@Param("email") String email);
}

