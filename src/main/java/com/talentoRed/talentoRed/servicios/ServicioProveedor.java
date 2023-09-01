package com.talentoRed.talentoRed.servicios;

import com.talentoRed.talentoRed.entidades.Proveedor;
import com.talentoRed.talentoRed.entidades.Usuario;
import com.talentoRed.talentoRed.enums.Disponibilidad;
import com.talentoRed.talentoRed.enums.MetodoPago;
import com.talentoRed.talentoRed.enums.Rol;
import com.talentoRed.talentoRed.enums.TipoServicio;
import com.talentoRed.talentoRed.myExceptions.MyException;
import com.talentoRed.talentoRed.repositorios.RepositorioProveedor;
import com.talentoRed.talentoRed.repositorios.RepositorioUsuario;
import java.util.ArrayList;
import java.util.Comparator;
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


/**
 * @author usuario
 */

@Service
public class ServicioProveedor implements UserDetailsService {
    
    @Autowired
    private RepositorioProveedor repoPro;
    //Agregar instancia de servicio Usuario
    @Autowired  
    private RepositorioUsuario repoUser;
    
    @Transactional
    public void crearProveedor(MultipartFile archivo, TipoServicio tipoServicio, String nombre, String email, String password,String password2,String telefono, 
          boolean tieneMatricula, String matricula, String descripcion, Disponibilidad disponibilidad, 
            MetodoPago metodoPago, MultipartFile portada){
        
        //validar 
        Proveedor proveedor = new Proveedor();
        proveedor.setServicio(tipoServicio);
        proveedor.setNombre(nombre);
        proveedor.setEmail(email);
        proveedor.setPassword(new BCryptPasswordEncoder().encode(password));
        proveedor.setRol(Rol.PROVEEDOR);
 proveedor.setTelefono(telefono);
        proveedor.setTieneMatricula(tieneMatricula);
        proveedor.setMatricula(matricula);
        proveedor.setDisponibilidad(disponibilidad);
        proveedor.setDescripcion(descripcion);
        proveedor.setMetodoPago(metodoPago);//configurar debe ser un List
        proveedor.setCantServ(0);
        //proveedor.setImagen(imagen);
        //proveedor.setPortada(portada);
        repoPro.save(proveedor);
    }
    
     public List<Proveedor> listarProveedor(TipoServicio servicio){
     List<Proveedor> proveedor = new ArrayList(); 
     
     repoPro.listarProveedorPorRubro(servicio);
     return proveedor;
    }
    
    //REVISAR IMPLEMENTACION DE LOS METODOS DEL PROVEEDOR

    /*      validar(nombre, email, password, password2);

        Usuario usuarioExistente = usuarioRepositorio.buscarUsuarioPorEmail(email);
        if (usuarioExistente != null) {
            throw new ValidationException("Ya existe un usuario registrado con ese email");
        }
        Imagen imagen = imagenServicio.guardar(archivo);
        usuario.setImagen(imagen);
        


    
     */
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Proveedor proveedor = repoPro.buscarProveedorPorEmail(email);
        if(proveedor != null){
            List<GrantedAuthority> permisos = new ArrayList();
            GrantedAuthority p = new SimpleGrantedAuthority("ROLE_" + proveedor.getRol().toString());
            permisos.add(p);  
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", proveedor);
             return new User(proveedor.getEmail(), proveedor.getPassword(), permisos);
        }else{
            return null; 
        }
        
    }
    
    
    
    private void validar(String nombre, String email, String password, String password2) throws MyException {
        if (email.isEmpty() || email == null) {
            throw new MyException("el email no puede ser nulo"); //
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
    
    public Proveedor getOne(String id){
        Proveedor proveedor = repoPro.getOne(id);
        return proveedor;
    }
    
    public List<Usuario> getProveedoresOrderedByTipoServicio() {
        List<Usuario> usuarios = repoUser.findAllProveedoresOrderedByTipoServicio();
        return usuarios;
    }
    
   public List<Proveedor> obtenerProveedoresOrdenados() {
        List<Proveedor> proveedores = repoPro.findAll();

        Comparator<Proveedor> comparador = Comparator.comparing(Proveedor::getServicio)
                .thenComparing(Proveedor::getNombre);
        //OTRA MANERA FUNCION COMPARE TO
//        Comparator<Proveedor> comparador = (p1, p2) -> {
//            int tipoComparison = p1.getServicio().compareTo(p2.getServicio());
//            if (tipoComparison != 0) {
//                return tipoComparison;
//            }
//            return p1.getNombre().compareTo(p2.getNombre());
//        };
        proveedores.sort(comparador);

        return proveedores;
    }
}
