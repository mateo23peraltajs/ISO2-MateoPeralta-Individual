package com.mycompany.jpaprueba;

import com.mycompany.jpaprueba.logica.Alumno;
import com.mycompany.jpaprueba.logica.Carrera;
import com.mycompany.jpaprueba.logica.Controladora;
import com.mycompany.jpaprueba.logica.Materia;
import java.util.Date;
import java.util.LinkedList;

public class JpaPrueba {

    //Test creado con IA para ver si funciona.

    public static void main(String[] args) {
        
        Controladora control = new Controladora();
        
        System.out.println("------ 1. CREANDO CARRERA ------");
        LinkedList<Materia> listaMaterias = new LinkedList<>();
        Carrera car = new Carrera(25, "Tecnicatura en Programación", listaMaterias);
        control.crearCarrera(car);
        System.out.println("Carrera creada: " + control.traerCarrera(25).getNombre());
        
        System.out.println("\n------ 2. CREANDO MATERIAS Y ASIGNANDO A CARRERA ------");
        Materia mate1 = new Materia(58, "Programacion I", "Cuatrimestral", car);
        Materia mate2 = new Materia(59, "Programacion II", "Cuatrimestral", car);
        Materia mate3 = new Materia(60, "Bases de Datos", "Anual", car);
        
        control.crearMateria(mate1);
        control.crearMateria(mate2);
        control.crearMateria(mate3);
        
        listaMaterias.add(mate1);
        listaMaterias.add(mate2);
        listaMaterias.add(mate3);
        car.setListaMaterias(listaMaterias);
        control.editarCarrera(car);
        System.out.println("Materias asignadas a la carrera. Total: " + control.traerCarrera(25).getListaMaterias().size());
        
        System.out.println("\n------ 3. CREANDO ALUMNO Y ASIGNANDO CARRERA ------");
        Alumno alu = new Alumno(23, "Libro", "de York", new Date(), car);
        control.crearAlumno(alu);
        Alumno aluEncontrado = control.traerAlumno(23);
        System.out.println("Alumno creado: " + aluEncontrado.getNombre() + " " + aluEncontrado.getApellido());
        System.out.println("Carrera del alumno: " + aluEncontrado.getUnacarrera().getNombre());
        
        System.out.println("\n------ 4. PROBANDO EDICIÓN (EDIT) ------");
        aluEncontrado.setNombre("NuevoNombre");
        aluEncontrado.setApellido("NuevoApellido");
        control.editarAlumno(aluEncontrado);
        System.out.println("Alumno editado: " + control.traerAlumno(23).getNombre() + " " + control.traerAlumno(23).getApellido());
        
        System.out.println("\n------ 5. PROBANDO LECTURA DE LISTAS ------");
        System.out.println("Lista de alumnos: " + control.traerListaAlumnos().size());
        System.out.println("Lista de materias: " + control.traerListaMaterias().size());
        System.out.println("Lista de carreras: " + control.traerListaCarreras().size());
        
        System.out.println("\n------ 6. PROBANDO ELIMINACIÓN (DESTROY) ------");
        control.eliminarAlumno(23);
        if(control.traerAlumno(23) == null) {
            System.out.println("El alumno fue eliminado correctamente.");
        }
        
        System.out.println("\n------ TEST COMPLETO FINALIZADO CON ÉXITO ------");
    }
}

