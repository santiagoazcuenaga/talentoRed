/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;


import entidades.Usuario;
import enums.Rol;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repositorios.repositorioUsuario;

/**
 *
 * @author Usuario
 */
@Service
public class servicioUsuario {
@Autowired
private repositorioUsuario repositorioUsuario;


@Transactional
public void crearUsuario(String id, String nombre, String email, String password, String direccion){
Usuario usuario = new Usuario();
usuario.setId(id);
usuario.setNombre(nombre);
usuario.setEmail(email);
usuario.setDireccion(direccion);
usuario.setPassword(new BCryptPasswordEncoder().encode(password));
usuario.setAlta(Boolean.FALSE);
usuario.setRol(Rol.User);
repositorioUsuario.save(usuario);
}
 

public List<Usuario> listarUsuarios(){
    List<Usuario> usuario = new ArrayList();
    usuario = repositorioUsuario.findAll();
    return usuario;
}

@Transactional
public void actualizarUsuario(){
    
}


@Transactional
public void darAlta(String id) {

        Optional<Usuario> respuesta = repositorioUsuario.findById(id);
        if (respuesta.isPresent()) {

            Usuario usuario = respuesta.get();

            if (Objects.equals(usuario.getAlta(), Boolean.TRUE)) {
                usuario.setAlta(Boolean.FALSE);
            } else {
                usuario.setAlta(Boolean.TRUE);
            }
            repositorioUsuario.save(usuario);

        }

    }



}
