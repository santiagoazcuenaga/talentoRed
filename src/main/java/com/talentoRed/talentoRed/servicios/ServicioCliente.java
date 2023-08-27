/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.servicios;

import com.talentoRed.talentoRed.entidades.Cliente;
import com.talentoRed.talentoRed.entidades.Imagen;
import com.talentoRed.talentoRed.enums.Barrio;
import com.talentoRed.talentoRed.enums.Rol;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.repositorios.RepositorioCliente;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import java.util.Optional;

/**
 * @author usuario
 */
@Service
public class ServicioCliente implements UserDetailsService {

    @Autowired
    private RepositorioCliente repositorioCliente;
    @Autowired
    private ServicioImagen servicioImagen;

    @Transactional
    public void crearCliente(MultipartFile archivo, String nombre, String email, String password,
            String password2, Barrio barrio, String manzana, int casa) throws MyException {

        validar(nombre, email, password, password2);
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setEmail(email);
        // usuario.setDireccion(direccion);
        cliente.setPassword(new BCryptPasswordEncoder().encode(password));
        cliente.setRol(Rol.CLIENTE);
        Imagen imagen = servicioImagen.guardar(archivo);
        cliente.setImagen(imagen);
        cliente.setManzana(manzana);
        cliente.setBarrio(barrio);
        cliente.setCasa(casa);
        repositorioCliente.save(cliente);

    }

    private void validar(String nombre, String email, String password, String password2) throws MyException {
        if (email.isEmpty() || email == null) {
            throw new MyException("el email no puede ser nulo");
        }
        if (nombre.isEmpty() || nombre == null) {
            throw new MyException("el nombre no puede estar vacio");
        }
        if (password.isEmpty() || password == null || password.length() < 8) {
            throw new MyException("la contraseña debe tener mas de 8 caracteres");
        }
        if (!password.equals(password2)) {
            throw new MyException("Las contraseñas no coinciden.");
        }
    }

    public List<Cliente> listarUsuarios() {
        List<Cliente> cliente = new ArrayList();
        cliente = repositorioCliente.findAll();
        return cliente;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cliente = repositorioCliente.buscarClientePorEmail(email);

        if (cliente != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + cliente.getRol().toString());
            permisos.add(p);

            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", cliente);
            return new User(email, email, permisos);
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
    }

    public Cliente getOne(String id) {
        Cliente cliente = repositorioCliente.getById(id);
        return cliente;
    }

    public void modificarCliente(String id, MultipartFile archivo, String nombre, String email, Barrio barrio, String manzana, int casa) throws MyException {

        //this.validar(nombre, email, password, password2);
        Optional<Cliente> respuesta = repositorioCliente.findById(id);

        if (respuesta.isPresent()) {
            Cliente cliente = respuesta.get();
            cliente.setNombre(nombre);
            cliente.setEmail(email);
            // usuario.setDireccion(direccion);
            //validar de forma aparte la contraseña
            //cliente.setPassword(new BCryptPasswordEncoder().encode(password));
            Imagen imagen = servicioImagen.guardar(archivo);
            cliente.setImagen(imagen);
            cliente.setManzana(manzana);
            cliente.setBarrio(barrio);
            cliente.setCasa(casa);
            repositorioCliente.save(cliente);
        }
    }
}
