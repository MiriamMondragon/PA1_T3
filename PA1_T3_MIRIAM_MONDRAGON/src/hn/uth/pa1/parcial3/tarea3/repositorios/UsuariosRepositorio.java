/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.pa1.parcial3.tarea3.repositorios;

import hn.uth.pa1.parcial3.tarea3.objetos.Usuarios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Miriam
 */
public class UsuariosRepositorio implements Repositorio<Usuarios> {

    private EntityManager em;

    public static EntityManager getEm() {
        try {
            String nombreEM = "PA1_T3_MIRIAM_MONDRAGONPU";
            return Persistence.createEntityManagerFactory(nombreEM).createEntityManager();
        } catch (Exception e) {
            throw new UnsupportedOperationException("Error al obtener la EM");
        }
    }

    private void iniciarTransaccion() {
        em = getEm();
        em.getTransaction().begin();
    }

    private void guardarCambios() {
        em.getTransaction().commit();
    }

    @Override
    public void crear(Usuarios t) throws Exception {
        try {
            iniciarTransaccion();
            em.persist(t);
            guardarCambios();
        } catch (Exception e) {
            throw new Exception("Error al insertar");
        }
    }

    @Override
    public void eliminar(Usuarios t) throws Exception {
        try {
            iniciarTransaccion();
            String identificador = t.getUsuario();
            Usuarios usuarioEliminar = buscar(identificador);
            em.remove(usuarioEliminar);
            guardarCambios();
        } catch (Exception e) {
            throw new Exception("Error al eliminar");
        }
    }

    @Override
    public void actualizar(Usuarios t) throws Exception {
        iniciarTransaccion();
        String identificador = t.getUsuario();
        Usuarios usuarioActualizar = buscar(identificador);
        if (usuarioActualizar.getUsuario().equals(t.getUsuario())) {
            usuarioActualizar.setContrasenia(t.getContrasenia());
            usuarioActualizar.setNombres(t.getNombres());
            usuarioActualizar.setApellidos(t.getApellidos());
            usuarioActualizar.setDireccion(t.getDireccion());
            usuarioActualizar.setTelefono(t.getTelefono());
            usuarioActualizar.setEstado(t.getEstado());

            em.persist(usuarioActualizar);
            guardarCambios();
        }
    }

    @Override
    public Usuarios buscar(Object id) throws Exception {
        Usuarios usuario;
        try {
            usuario = em.find(Usuarios.class, id);
            return usuario;
        } catch (Exception e) {
            throw new Exception("Error al buscar el registro del usuario " + id);
        }
    }

    @Override
    public List<Usuarios> buscarTodo() throws Exception {
        iniciarTransaccion();
        return em.createQuery("SELECT u FROM Usuarios u").getResultList();
    }

}
