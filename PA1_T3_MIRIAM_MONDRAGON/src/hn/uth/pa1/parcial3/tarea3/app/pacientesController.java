/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hn.uth.pa1.parcial3.tarea3.app;

import hn.uth.pa1.parcial3.tarea3.objetos.Pacientes;
import hn.uth.pa1.parcial3.tarea3.repositorios.PacientesRepositorio;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Alumno
 */
public class pacientesController {

    static PacientesRepositorio paciente = new PacientesRepositorio();

    static String[] columnasTablaPacientes = new String[]{
        "Identidad", "Nombres", "Apellidos", "Teléfono", "Dirección",
        "Departamento de Nacimiento", "Municipio de Nacimiento", "Estado",
        "Sintomas", "Fecha de Ingreso", "Fecha de Nacimiento", "Días en el Hospital"
    };

    public static Object[][] convertirLista(List<Pacientes> pacientes) {

        Object arreglo[][] = new Object[pacientes.size()][columnasTablaPacientes.length];

        int indice = pacientes.size() - 1;
        for (Pacientes pacienteTmp : pacientes) {
            String identidad = pacienteTmp.getIdentidad();
            String nombres = pacienteTmp.getNombres();
            String apellidos = pacienteTmp.getApellidos();
            String direccion = pacienteTmp.getDireccion();
            String telefono = pacienteTmp.getTelefono();
            String departamento = pacienteTmp.getDepartamentoNacimiento();
            String municipio = pacienteTmp.getMunicipioNacimiento();
            String estado = pacienteTmp.getEstado();
            String sintomas = pacienteTmp.getSintomas();

            char[] digitosFechaIngreso = pacienteTmp.getFechaIngreso().toString().toCharArray();
            String anio = "" + digitosFechaIngreso[0] + digitosFechaIngreso[1] + digitosFechaIngreso[2] + digitosFechaIngreso[3];
            String mes = "" + digitosFechaIngreso[4] + digitosFechaIngreso[5];
            String dia = "" + digitosFechaIngreso[6] + digitosFechaIngreso[7];
            String fechaIngreso = anio + "-" + mes + "-" + dia;

            char[] digitosFechaNacimiento = pacienteTmp.getFechaNacimiento().toString().toCharArray();
            String anio1 = "" + digitosFechaNacimiento[0] + digitosFechaNacimiento[1] + digitosFechaNacimiento[2] + digitosFechaNacimiento[3];
            String mes1 = "" + digitosFechaNacimiento[4] + digitosFechaNacimiento[5];
            String dia1 = "" + digitosFechaNacimiento[6] + digitosFechaNacimiento[7];
            String fechaNacimiento = anio1 + "-" + mes1 + "-" + dia1;

            int diasIngresado = calcularDias(Integer.valueOf(dia), Integer.valueOf(mes), Integer.valueOf(anio));

            arreglo[indice][0] = identidad;
            arreglo[indice][1] = nombres;
            arreglo[indice][2] = apellidos;
            arreglo[indice][3] = direccion;
            arreglo[indice][4] = telefono;
            arreglo[indice][5] = departamento;
            arreglo[indice][6] = municipio;
            arreglo[indice][7] = estado;
            arreglo[indice][8] = sintomas;
            arreglo[indice][9] = fechaIngreso;
            arreglo[indice][10] = fechaNacimiento;
            arreglo[indice][11] = diasIngresado;
            indice--;
        }
        return arreglo;
    }

    public static DefaultTableModel getTblPacientes() throws Exception {
        List<Pacientes> listaPacientesBD = paciente.buscarTodo();

        DefaultTableModel defaultTableModel = new DefaultTableModel(
                convertirLista(listaPacientesBD),
                columnasTablaPacientes
        );
        return defaultTableModel;
    }

    public static Object[][] convertirListaFiltrada(List<Pacientes> pacientes, String filtro) {

        Object arreglo[][] = new Object[pacientes.size()][columnasTablaPacientes.length];

        int indice = pacientes.size() - 1;
        int indiceFinal = 0;
        for (Pacientes pacienteTmp : pacientes) {
            if (pacienteTmp.getEstado().equals(filtro)) {
                String identidad = pacienteTmp.getIdentidad();
                String nombres = pacienteTmp.getNombres();
                String apellidos = pacienteTmp.getApellidos();
                String direccion = pacienteTmp.getDireccion();
                String telefono = pacienteTmp.getTelefono();
                String departamento = pacienteTmp.getDepartamentoNacimiento();
                String municipio = pacienteTmp.getMunicipioNacimiento();
                String estado = pacienteTmp.getEstado();
                String sintomas = pacienteTmp.getSintomas();

                char[] digitosFechaIngreso = pacienteTmp.getFechaIngreso().toString().toCharArray();
                String anio = "" + digitosFechaIngreso[0] + digitosFechaIngreso[1] + digitosFechaIngreso[2] + digitosFechaIngreso[3];
                String mes = "" + digitosFechaIngreso[4] + digitosFechaIngreso[5];
                String dia = "" + digitosFechaIngreso[6] + digitosFechaIngreso[7];
                String fechaIngreso = anio + "-" + mes + "-" + dia;

                char[] digitosFechaNacimiento = pacienteTmp.getFechaNacimiento().toString().toCharArray();
                String anio1 = "" + digitosFechaNacimiento[0] + digitosFechaNacimiento[1] + digitosFechaNacimiento[2] + digitosFechaNacimiento[3];
                String mes1 = "" + digitosFechaNacimiento[4] + digitosFechaNacimiento[5];
                String dia1 = "" + digitosFechaNacimiento[6] + digitosFechaNacimiento[7];
                String fechaNacimiento = anio1 + "-" + mes1 + "-" + dia1;

                int diasIngresado = calcularDias(Integer.valueOf(dia), Integer.valueOf(mes), Integer.valueOf(anio));

                arreglo[indiceFinal][0] = identidad;
                arreglo[indiceFinal][1] = nombres;
                arreglo[indiceFinal][2] = apellidos;
                arreglo[indiceFinal][3] = direccion;
                arreglo[indiceFinal][4] = telefono;
                arreglo[indiceFinal][5] = departamento;
                arreglo[indiceFinal][6] = municipio;
                arreglo[indiceFinal][7] = estado;
                arreglo[indiceFinal][8] = sintomas;
                arreglo[indiceFinal][9] = fechaIngreso;
                arreglo[indiceFinal][10] = fechaNacimiento;
                arreglo[indiceFinal][11] = diasIngresado;
                indiceFinal++;
            }
            indice--;
        }
        return arreglo;
    }

    public static DefaultTableModel getTblPacientesFiltrados(String filtro) throws Exception {
        List<Pacientes> listaPacientesBD = paciente.buscarTodo();

        DefaultTableModel defaultTableModel = new DefaultTableModel(
                convertirListaFiltrada(listaPacientesBD, filtro),
                columnasTablaPacientes
        );
        return defaultTableModel;
    }

    public static void guardarPaciente(Pacientes pt) throws Exception {
        paciente.crear(pt);
    }

    public static void modificarPaciente(Pacientes pt) throws Exception {
        paciente.actualizar(pt);
    }

    public static void eliminarPaciente(Pacientes pt) throws Exception {
        paciente.eliminar(pt);
    }

    public static int calcularDias(int dia, int mes, int anio) {
        Calendar inicio = Calendar.getInstance();
        inicio.set(anio, mes - 1, dia);
        inicio.set(Calendar.HOUR, 0);
        inicio.set(Calendar.HOUR_OF_DAY, 0);
        inicio.set(Calendar.MINUTE, 0);
        inicio.set(Calendar.SECOND, 0);

        Calendar actual = Calendar.getInstance();
        actual.set(Calendar.HOUR, 0);
        actual.set(Calendar.HOUR_OF_DAY, 0);
        actual.set(Calendar.MINUTE, 0);
        actual.set(Calendar.SECOND, 0);

        long finMS = actual.getTimeInMillis();
        long inicioMS = inicio.getTimeInMillis();
        int dias = (int) ((Math.abs(finMS - inicioMS)) / (1000 * 60 * 60 * 24));
        return dias;
    }
}
