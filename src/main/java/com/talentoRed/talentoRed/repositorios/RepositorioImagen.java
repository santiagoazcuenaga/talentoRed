/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.talentoRed.talentoRed.repositorios;

import com.talentoRed.talentoRed.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Kidver
 */
@Repository
public interface RepositorioImagen extends JpaRepository<Imagen, String> {

}
