/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.comparator;

import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Rol;
import java.util.Comparator;

/**
 * @author lukaku
 */

public class UsuarioRolComparator implements Comparator<Usuario> {

    @Override
    public int compare(Usuario t, Usuario t1) {
        //obtener los roles
        Rol rolt = t.getRol();
        Rol rolt1 = t1.getRol();
        //compara los roles
        return Integer.compare(rolt1.ordinal(),rolt.ordinal());
    }
    
}
