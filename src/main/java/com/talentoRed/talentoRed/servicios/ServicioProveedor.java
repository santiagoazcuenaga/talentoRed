
package com.talentoRed.talentoRed.servicios;

import com.talentoRed.talentoRed.entidades.Imagen;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Rol;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.repositorios.repositorioUsuario;
import javax.transaction.Transactional;
import javax.xml.bind.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author usuario
 */
@Service
public class ServicioProveedor extends servicioUsuario {
    //REVISAR IMPLEMENTACION DE LOS METODOS DEL PROVEEDOR
    
    /*@Autowired
    private repositorioUsuario usuarioRepositorio;
    @Autowired
    private ServicioImagen imagenServicio;

    @Transactional
    public void registrar(MultipartFile archivo, String nombre, String email, String password, String password2) throws MyException, ValidationException {
        validar(nombre, email, password, password2);

        Usuario usuarioExistente = usuarioRepositorio.buscarUsuarioPorEmail(email);
        if (usuarioExistente != null) {
            throw new ValidationException("Ya existe un usuario registrado con ese email");
        }

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setRol(Rol.PROVEEDOR);
        

        Imagen imagen = imagenServicio.guardar(archivo);
        usuario.setImagen(imagen);
        
        //Agregar todos los datos específicos del Proveedor

        
        usuarioRepositorio.save(usuario);
    }
    *///OTRA OPCION ES UTILIZAR LOS METODOS DE LA CLASE USUARIO
    
    /*/Importando un servicio de usuario
    @Autowired
    servicioUsuario servicioUsuario = new servicioUsuario();
    
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private void validar(String nombre, String email, String password,String password2) throws MyException {
        if (email.isEmpty() || email == null) {
            throw new MyException("el email no puede ser nulo"); //
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("el nombre no puede estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() < 8) {
            throw new MyException("la contraseña debe tener mas de 8 caracteres");
        }
        if(!password.equals(password2)){
           throw new MyException("Las contraseñas no coinciden.");
       }
    }
}