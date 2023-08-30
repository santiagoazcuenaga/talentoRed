/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.entidades;

import com.talentoRed.talentoRed.enums.Disponibilidad;
import com.talentoRed.talentoRed.enums.MetodoPago;
import com.talentoRed.talentoRed.enums.Rol;
import com.talentoRed.talentoRed.enums.TipoServicio;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;

/**
 *
 * @author Usuario
 */
@Entity
public class Proveedor extends Usuario {

    @Enumerated(EnumType.STRING)
    private TipoServicio servicio;

    private Boolean tieneMatricula;
    private String matricula;
    private int calificacion;
    @Enumerated(EnumType.STRING)
    private Disponibilidad disponibilidad;
    private String Descripcion;
    @Enumerated(EnumType.STRING)
    private MetodoPago metodoPago;
    private int cantServ;
    @OneToOne
    private Imagen portada;

    public Proveedor() {
    }

    public TipoServicio getServicio() {
        return servicio;
    }

    public void setServicio(TipoServicio servicio) {
        this.servicio = servicio;
    }

    public Boolean getTieneMatricula() {
        return tieneMatricula;
    }

    public void setTieneMatricula(Boolean tieneMatricula) {
        this.tieneMatricula = tieneMatricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
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

    public Imagen getPortada() {
        return portada;
    }

    public void setPortada(Imagen portada) {
        this.portada = portada;
    }

}
