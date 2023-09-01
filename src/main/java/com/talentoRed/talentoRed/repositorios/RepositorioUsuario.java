/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.repositorios;

import com.talentoRed.talentoRed.entidades.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Usuario
 */
@Repository
public interface RepositorioUsuario extends JpaRepository<Usuario,String> {
    @Query("SELECT a FROM Usuario a WHERE a.email = :email ")
    public Usuario buscarUsuarioPorEmail(@Param("email") String email );
    
    @Query("SELECT u FROM Usuario u WHERE u.rol = 'Proveedor'")
    public List<Usuario> findAllProveedoresOrderedByTipoServicio();
}
