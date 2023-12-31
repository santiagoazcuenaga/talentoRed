/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.entidades;

import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.EstadoSolicitud;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;


/**
 * @authors Kidver y lukaku
 */

@Entity
public class OrdenDeServicio {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @OneToOne
    private Usuario usuario;//el Cliente que realiza la solicitud
    @OneToOne
    private Proveedor proveedor;//El proveedor que acepta o rechaza la solicitud CREAR METODO ACEPTAR QUE CONTENGA EL ESTADODELSERVICIO
    @Enumerated(EnumType.STRING)
    private EstadoSolicitud estadoServicio; // EN proceso - Finalizado LO MODIFICA EL PROVEEDOR CREAR METODO.
    private Integer calificacion; //CALIFICA LUEGO DE LA ORDEN EL CLIENTE
    private String comentario; //OPCIONAL PARTE DEL CLIENTE.

  

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public EstadoSolicitud getEstadoServicio() {
        return estadoServicio;
    }

    public void setEstadoServicio(EstadoSolicitud estadoServicio) {
        this.estadoServicio = estadoServicio;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}//The end
