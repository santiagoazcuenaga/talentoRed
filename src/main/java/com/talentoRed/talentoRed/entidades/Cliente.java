/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.entidades;

import com.talentoRed.talentoRed.enums.Barrio;
import com.talentoRed.talentoRed.enums.Rol;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author usuario
 */
@Entity
public class Cliente extends Usuario{
    
    @Enumerated(EnumType.STRING)
    private Barrio barrio;
    private String manzana;
    private int casa;
    private String telefono;

    public Cliente(String id, String nombre, String email, String password, String direccion, Rol rol) {
        super(id, nombre, email, password, direccion, rol);
    }

    public Cliente() {
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getManzana() {
        return manzana;
    }

    public void setManzana(String manzana) {
        this.manzana = manzana;
    }

    public int getCasa() {
        return casa;
    }

    public void setCasa(int casa) {
        this.casa = casa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    
}
