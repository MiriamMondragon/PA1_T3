/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.pa1.parcial3.tarea3.repositorios;

import hn.uth.pa1.parcial3.tarea3.objetos.Pacientes;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Miriam
 */
public class PacientesRepositorio implements Repositorio<Pacientes> {

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
    public void crear(Pacientes t) throws Exception {
        try {
            iniciarTransaccion();
            em.persist(t);
            guardarCambios();
        } catch (Exception e) {
            throw new Exception("Error al insertar");
        }
    }

    @Override
    public void eliminar(Pacientes t) throws Exception {
        try {
            iniciarTransaccion();
            String identificador = t.getIdentidad();
            Pacientes pacienteEliminar = buscar(identificador);
            em.remove(pacienteEliminar);
            guardarCambios();
        } catch (Exception e) {
            throw new Exception("Error al eliminar");
        }
    }

    @Override
    public void actualizar(Pacientes t) throws Exception {
        iniciarTransaccion();
        String identificador = t.getIdentidad();
        Pacientes pacienteActualizar = buscar(identificador);
        if (pacienteActualizar.getIdentidad().equals(t.getIdentidad())) {
            pacienteActualizar.setNombres(t.getNombres());
            pacienteActualizar.setApellidos(t.getApellidos());
            pacienteActualizar.setDireccion(t.getDireccion());
            pacienteActualizar.setTelefono(t.getTelefono());
            pacienteActualizar.setDepartamentoNacimiento(t.getDepartamentoNacimiento());
            pacienteActualizar.setMunicipioNacimiento(t.getMunicipioNacimiento());
            pacienteActualizar.setEstado(t.getEstado());
            pacienteActualizar.setSintomas(t.getSintomas());
            pacienteActualizar.setFechaIngreso(t.getFechaIngreso());
            pacienteActualizar.setFechaNacimiento(t.getFechaNacimiento());

            em.persist(pacienteActualizar);
            guardarCambios();
        }
    }

    @Override
    public Pacientes buscar(Object id) throws Exception {
        Pacientes paciente;
        try {
            paciente = em.find(Pacientes.class, id);
            return paciente;
        } catch (Exception e) {
            throw new Exception("Error al buscar al paciente con identidad " + id);
        }
    }

    @Override
    public List<Pacientes> buscarTodo() throws Exception {
        iniciarTransaccion();
        return em.createQuery("SELECT p FROM Pacientes p").getResultList();
    }

}
