/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.pa1.parcial3.tarea3.app;

import hn.uth.pa1.parcial3.tarea3.objetos.Usuarios;
import hn.uth.pa1.parcial3.tarea3.repositorios.UsuariosRepositorio;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Miriam
 */
public class usuariosController {
    
    static UsuariosRepositorio usuario = new UsuariosRepositorio();
    
    static String[] columnasTablaUsuarios = new String[]{
        "Usuario", "Contraseña", "Nombres", "Apellidos", "Teléfono", "Dirección", "Estado"
    };
    
    public static Object[][] convertirLista(List<Usuarios> usuarios) {
        
        Object arreglo[][] = new Object[usuarios.size()][columnasTablaUsuarios.length];
        
        int indice = usuarios.size() - 1;
        for (Usuarios usuarioTmp : usuarios) {
            String usuario = usuarioTmp.getUsuario();
            String contrasenia = usuarioTmp.getContrasenia();
            String nombres = usuarioTmp.getNombres();
            String apellidos = usuarioTmp.getApellidos();
            String direccion = usuarioTmp.getDireccion();
            String telefono = usuarioTmp.getTelefono();
            String estado = usuarioTmp.getEstado();
            
            arreglo[indice][0] = usuario;
            arreglo[indice][1] = contrasenia;
            arreglo[indice][2] = nombres;
            arreglo[indice][3] = apellidos;
            arreglo[indice][4] = direccion;
            arreglo[indice][5] = telefono;
            arreglo[indice][6] = estado;
            indice--;
        }
        return arreglo;
    }
    
    public static DefaultTableModel getTblUsuarios() throws Exception {
        List<Usuarios> listaUsuariosBD = usuario.buscarTodo();
        
        DefaultTableModel defaultTableModel = new DefaultTableModel(
                convertirLista(listaUsuariosBD),
                columnasTablaUsuarios
        );
        return defaultTableModel;
    }
    
    public static Object[][] convertirListaFiltrada(List<Usuarios> usuarios, String filtro) {
        
        Object arreglo[][] = new Object[usuarios.size()][columnasTablaUsuarios.length];
        
        int indice = usuarios.size() - 1;
        int indiceFinal = 0;
        for (Usuarios usuarioTmp : usuarios) {
            if (usuarioTmp.getEstado().equals(filtro)) {
                String usuario = usuarioTmp.getUsuario();
                String contrasenia = usuarioTmp.getContrasenia();
                String nombres = usuarioTmp.getNombres();
                String apellidos = usuarioTmp.getApellidos();
                String direccion = usuarioTmp.getDireccion();
                String telefono = usuarioTmp.getTelefono();
                String estado = usuarioTmp.getEstado();
                
                arreglo[indiceFinal][0] = usuario;
                arreglo[indiceFinal][1] = contrasenia;
                arreglo[indiceFinal][2] = nombres;
                arreglo[indiceFinal][3] = apellidos;
                arreglo[indiceFinal][4] = direccion;
                arreglo[indiceFinal][5] = telefono;
                arreglo[indiceFinal][6] = estado;
                indiceFinal++;
            }
            indice--;
        }
        return arreglo;
    }
    
    public static DefaultTableModel getTblUsuariosFiltrados(String filtro) throws Exception {
        List<Usuarios> listaUsuariosBD = usuario.buscarTodo();
        
        DefaultTableModel defaultTableModel = new DefaultTableModel(
                convertirListaFiltrada(listaUsuariosBD, filtro),
                columnasTablaUsuarios
        );
        return defaultTableModel;
    }
    
    public static void guardarUsuario(Usuarios user) throws Exception {
        usuario.crear(user);
    }
    
    public static void modificarUsuario(Usuarios user) throws Exception {
        usuario.actualizar(user);
    }
    
    public static void eliminarUsuario(Usuarios user) throws Exception {
        usuario.eliminar(user);
    }
    
    public static void buscarUsuario(Usuarios user) throws Exception {
        usuario.buscar(user);
    }
    
    public static List<Usuarios> buscarTodosActivos() throws Exception {
        List<Usuarios> usuariosSinFiltro = usuario.buscarTodo();
        List<Usuarios> usuariosActivos = new ArrayList<>();
        for (Usuarios usuarioTmp : usuariosSinFiltro) {
            if (usuarioTmp.getEstado().equals("Activo")) {
                usuariosActivos.add(usuarioTmp);
            }
        }
        return usuariosActivos;
    }
    
}
