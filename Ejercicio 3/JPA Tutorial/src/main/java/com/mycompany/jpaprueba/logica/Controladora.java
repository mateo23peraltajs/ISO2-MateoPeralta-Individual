package com.mycompany.jpaprueba.logica;

import com.mycompany.jpaprueba.persistencia.ControladoraPersistencia;

public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();

    // ==========================================
    // ALUMNO
    // ==========================================
    
    public void crearAlumno(Alumno alumno) {
        controlPersis.crearAlumno(alumno);
    }
    
    public void eliminarAlumno(int id) {
        controlPersis.eliminarAlumno(id);
    }
    
    public void editarAlumno(Alumno alu) {
        controlPersis.editarAlumno(alu);
    }
    
    public Alumno traerAlumno(int id) {
        return controlPersis.traerAlumno(id);
    }
    
    public java.util.ArrayList<Alumno> traerListaAlumnos() {
        return controlPersis.traerListaAlumnos();
    }
    
    // ==========================================
    // CARRERA
    // ==========================================
    
    public void crearCarrera(Carrera carrera) {
        controlPersis.crearCarrera(carrera);
    }
    
    public void eliminarCarrera(int id) {
        controlPersis.eliminarCarrera(id);
    }
    
    public void editarCarrera(Carrera carrera) {
        controlPersis.editarCarrera(carrera);
    }
    
    public Carrera traerCarrera(int id) {
        return controlPersis.traerCarrera(id);
    }
    
    public java.util.ArrayList<Carrera> traerListaCarreras() {
        return controlPersis.traerListaCarreras();
    }
    
    // ==========================================
    // MATERIA
    // ==========================================
    
    public void crearMateria(com.mycompany.jpaprueba.logica.Materia materia) {
        controlPersis.crearMateria(materia);
    }
    
    public void eliminarMateria(int id) {
        controlPersis.eliminarMateria(id);
    }
    
    public void editarMateria(com.mycompany.jpaprueba.logica.Materia materia) {
        controlPersis.editarMateria(materia);
    }
    
    public com.mycompany.jpaprueba.logica.Materia traerMateria(int id) {
        return controlPersis.traerMateria(id);
    }
    
    public java.util.ArrayList<com.mycompany.jpaprueba.logica.Materia> traerListaMaterias() {
        return controlPersis.traerListaMaterias();
    }
}
