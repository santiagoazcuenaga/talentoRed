/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.entidades;

import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Disponibilidad;
import com.talentoRed.talentoRed.enums.MetodoPago;
import com.talentoRed.talentoRed.enums.Rol;
import com.talentoRed.talentoRed.enums.TipoServicio;
import javax.persistence.Entity;

/**
 *
 * @author Usuario
 */
@Entity
public class Proveedor extends Usuario {


    private TipoServicio servicio;
    private int nroContacto;
    private Boolean tieneMatricula;
    private int matricula;
    private int calificacion;
    private Disponibilidad disponibilidad;
    private String Descripcion;
    private MetodoPago metodoPago;
    private int cantServ;
       
    // private String nroIdentificador;  investigar metodo generador, 
    public Proveedor() {
    }

    //Contructor
    public Proveedor(String id, String nombre, String email, String password, String direccion, Rol rol) {
        super(id, nombre, email, password, direccion, rol);
        this.setRol(Rol.PROVEEDOR); 
    }

    public TipoServicio getServicio() {
        return servicio;
    }

    public void setServicio(TipoServicio servicio) {
        this.servicio = servicio;
    }

    public int getNroContacto() {
        return nroContacto;
    }

    public void setNroContacto(int nroContacto) {
        this.nroContacto = nroContacto;
    }

    public Boolean getTieneMatricula() {
        return tieneMatricula;
    }

    public void setTieneMatricula(Boolean tieneMatricula) {
        this.tieneMatricula = tieneMatricula;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public Disponibilidad getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(Disponibilidad disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public int getCantServ() {
        return cantServ;
    }

    public void setCantServ(int cantServ) {
        this.cantServ = cantServ;
    }
    
    
    

}
