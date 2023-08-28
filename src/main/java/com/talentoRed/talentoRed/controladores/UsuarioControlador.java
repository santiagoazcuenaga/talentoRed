/*
 * EN ESTA CLASE DEBE DARSE LA LOGICA DE TODO USUARIO DE LA APP
 */
package com.talentoRed.talentoRed.controladores;
import com.talentoRed.talentoRed.servicios.ServicioUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lukaku
 */
@Controller
@RequestMapping("/usuario")
public class UsuarioControlador {
    
    @Autowired
    ServicioUsuario usuarioservicio = new ServicioUsuario();
    
    @GetMapping("/{id}") // ruta para ver el perfil de un usuario
    public String perfilUsuario(@PathVariable("id") String id, ModelMap modelo) {

        modelo.put("user", usuarioservicio.getOne(id));
        return "perfil.html";

    }
    
}
