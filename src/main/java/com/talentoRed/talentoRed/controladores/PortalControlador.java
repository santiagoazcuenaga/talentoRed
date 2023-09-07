/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.talentoRed.talentoRed.controladores;

import com.talentoRed.talentoRed.entidades.Cliente;
import com.talentoRed.talentoRed.entidades.OrdenDeServicio;
import com.talentoRed.talentoRed.entidades.Proveedor;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Barrio;
import com.talentoRed.talentoRed.enums.TipoServicio;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.servicios.ServicioCliente;
import com.talentoRed.talentoRed.servicios.ServicioOrden;
import com.talentoRed.talentoRed.servicios.ServicioProveedor;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author usuario
 */
@Controller
@RequestMapping("/")
public class PortalControlador {

    @Autowired
    private ServicioCliente serCli;
    @Autowired
    private ServicioProveedor serPro;
    @Autowired
    private ServicioOrden serOrden;

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/registrarCliente")
    public String registrar() {
        return "registro.html";
    }

    @PostMapping("/registroCliente")
    public String registrar(MultipartFile archivo, @RequestParam String nombre, @RequestParam String email,
            String password, String password2, String telefono, Barrio barrio, String manzana, int casa) {
        try {
            serCli.crearCliente(archivo, nombre, email, password, password2, telefono, barrio, manzana, casa);
            return "redirect:/";

        } catch (MyException e) {
            // Error durante el registro, mostrar mensaje de error en la página de registro
            // Puedes agregar el mensaje de error a través del Model y mostrarlo en la plantilla
            // o redirigir a una página de error personalizada
            System.out.println(e.getMessage());
            return "registro.html";
        }
    }

    @GetMapping("/login")
    public String login(@RequestParam(required = false) String error, ModelMap modelo) {

        if (error != null) {
            System.out.println("Usuario o contraseña invalida");
            modelo.put("error", "Informacion invalida");
        }
        return "login.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN', 'ROLE_CLIENTE', 'ROLE_PROVEEDOR')")
    @GetMapping("/inicio")
    public String inicio(HttpSession session, ModelMap modelo) {
        try {
            // envia los datos del usuario a la pagina una vez este logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            modelo.put("user", logueado);
            // para admin hacer una vista diferente
            if (logueado.getRol().toString().equals("ADMIN")) {
                return "redirect:/admin/dashboard";
            }
            return "inicio.html";
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return "index.html";

        }
    }

    @PreAuthorize("hasAnyRole('ROLE_CLIENTE')")
    @GetMapping("/mi_perfil/{id}")
    public String perfil(@PathVariable String id, ModelMap modelo) {

        Cliente cliente = serCli.getOne(id);
        modelo.put("user", cliente);
        List<OrdenDeServicio> ordenes = serOrden.listarOrdenCliente(id);
        modelo.put("ordenes", ordenes);

        return "clientePerfil.html";
    }

    @GetMapping("/editar_perfil/{id}")
    public String editar_perfil(@PathVariable String id, ModelMap modelo) {

        Cliente cliente = serCli.getOne(id);
        modelo.put("user", cliente);
        
        return "actualizarCliente.html";
    }

    // falta MultipartFile archivo, password, 
    @PostMapping("/editar_perfil/{id}")
    public String editar_perfil(@PathVariable String id, MultipartFile archivo, @RequestParam String nombre, @RequestParam String email,
            Barrio barrio, String telefono, String manzana, int casa, ModelMap modelo) {

        try {
            serCli.modificarCliente(id, archivo, nombre, email, telefono, barrio, manzana, casa);
            modelo.put("exito", "Tu perfil ha sido actualizado!!");
            return this.perfil(id, modelo);
        } catch (MyException ex) {
            modelo.put("error", ex.getMessage());
            return this.editar_perfil(id, modelo);
        }
    }

@GetMapping("/proveedores")
    public String Proveedores(ModelMap modelo, TipoServicio servicio, HttpSession session) {
        try {
            // Envía los datos del usuario a la página una vez esté logueado
            Usuario logueado = (Usuario) session.getAttribute("usuariosession");
            modelo.put("user", logueado);
            List<Proveedor> proveedores = serPro.listarProveedor(servicio);
            modelo.addAttribute("usuarios", proveedores);
            return "ordenarProveedores.html";
        } catch(Exception e){
            return "usuario no encontrado";
        }
    }

        @GetMapping("/comofun")
        public String comofun
        (ModelMap modelo, HttpSession session) {
        try {
                // envia los datos del usuario a la pagina una vez este logueado
                Usuario logueado = (Usuario) session.getAttribute("usuariosession");
                modelo.put("user", logueado);
                // para dirigirlo a la pagina de Como Funciona
                return "comofunciona.html";

            } catch (Exception e) {
                System.out.println(e.getMessage());
                return "index.html";

            }
        }

    }