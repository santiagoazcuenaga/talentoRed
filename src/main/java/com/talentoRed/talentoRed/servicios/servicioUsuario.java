/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.servicios;

import com.talentoRed.talentoRed.entidades.Imagen;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Rol;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import com.talentoRed.talentoRed.myExceptions.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.talentoRed.talentoRed.repositorios.repositorioUsuario;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Usuario
 */
@Service
public class servicioUsuario implements UserDetailsService {

    @Autowired
    private repositorioUsuario repositorioUsuario;
    @Autowired
    private ServicioImagen servicioImagen;

    @Transactional // falta parametro de direccion en el formulario
    public void crearUsuario( MultipartFile archivo, String nombre, String email, String password,String password2) throws MyException {
        
        validar(nombre, email, password,password2);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
       // usuario.setDireccion(direccion);
        usuario.setPassword(new BCryptPasswordEncoder().encode(password));
        usuario.setAlta(Boolean.FALSE);
        usuario.setRol(Rol.USER);
        Imagen imagen = servicioImagen.guardar(archivo);
        usuario.setImagen(imagen);
        repositorioUsuario.save(usuario);

    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuario = new ArrayList();
        usuario = repositorioUsuario.findAll();
        return usuario;
    }

    @Transactional
    public void actualizarUsuario(String idUsuario, String nombre, String email, String password, String direccion,String password2) throws MyException {
        validar(nombre, email, password,password2);
        Optional<Usuario> respuesta = repositorioUsuario.findById(idUsuario);
        Usuario usuario = respuesta.get();

        if (respuesta.isPresent()) {
            usuario.setNombre(nombre);
            usuario.setEmail(email);
            usuario.setDireccion(direccion);
            usuario.setPassword(new BCryptPasswordEncoder().encode(password));
            usuario.setRol(Rol.USER);
            repositorioUsuario.save(usuario);
        }

    }

    @Transactional
    public void eliminarUsuario(String idUsuario) {
        Optional<Usuario> respuesta = repositorioUsuario.findById(idUsuario);
        Usuario usuario = respuesta.get();
        if (respuesta.isPresent()) {
            repositorioUsuario.delete(usuario);
        }
    }

    public Usuario getOne(String id) {
        return repositorioUsuario.getOne(id);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repositorioUsuario.buscarUsuarioPorEmail(email);

        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();

            HttpSession session = attr.getRequest().getSession(true);

            session.setAttribute("usuariosession", usuario);
            return new Usuario(usuario.getEmail(), usuario.getPassword(), permisos);
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    public Usuario obtenerUsuarioActual() {
        Authentication auth = (Authentication) SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getUsername();
        return repositorioUsuario.buscarUsuarioPorEmail(email);
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
