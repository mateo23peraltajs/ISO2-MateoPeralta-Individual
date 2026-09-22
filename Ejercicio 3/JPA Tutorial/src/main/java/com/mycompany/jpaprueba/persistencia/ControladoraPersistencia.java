package com.mycompany.jpaprueba.persistencia;

import com.mycompany.jpaprueba.logica.Alumno;

public class ControladoraPersistencia {
    
    AlumnoJpaController alumJpa = new AlumnoJpaController();

    // ==========================================
    // ALUMNO
    // ==========================================
    
    public void crearAlumno(Alumno alumno) {
        alumJpa.create(alumno);
    }
    
    public void eliminarAlumno(int id) {
        try {
            alumJpa.destroy(id);
        } catch (com.mycompany.jpaprueba.persistencia.exceptions.NonexistentEntityException ex) {
            java.util.logging.Logger.getLogger(ControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void editarAlumno(Alumno alu) {
        try {
            alumJpa.edit(alu);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public Alumno traerAlumno(int id) {
        return alumJpa.findAlumno(id);
    }
    
    public java.util.ArrayList<Alumno> traerListaAlumnos() {
        java.util.List<Alumno> lista = alumJpa.findAlumnoEntities();
        java.util.ArrayList<Alumno> listaAlumnos = new java.util.ArrayList<>(lista);
        return listaAlumnos;
    }
    
    // ==========================================
    // CARRERA
    // ==========================================
    
    CarreraJpaController carreraJpa = new CarreraJpaController();
    
    public void crearCarrera(com.mycompany.jpaprueba.logica.Carrera carrera) {
        carreraJpa.create(carrera);
    }
    
    public void eliminarCarrera(int id) {
        try {
            carreraJpa.destroy(id);
        } catch (com.mycompany.jpaprueba.persistencia.exceptions.NonexistentEntityException ex) {
            java.util.logging.Logger.getLogger(ControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void editarCarrera(com.mycompany.jpaprueba.logica.Carrera carrera) {
        try {
            carreraJpa.edit(carrera);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public com.mycompany.jpaprueba.logica.Carrera traerCarrera(int id) {
        return carreraJpa.findCarrera(id);
    }
    
    public java.util.ArrayList<com.mycompany.jpaprueba.logica.Carrera> traerListaCarreras() {
        java.util.List<com.mycompany.jpaprueba.logica.Carrera> lista = carreraJpa.findCarreraEntities();
        return new java.util.ArrayList<>(lista);
    }
    
    // ==========================================
    // MATERIA
    // ==========================================
    
    MateriaJpaController materiaJpa = new MateriaJpaController();
    
    public void crearMateria(com.mycompany.jpaprueba.logica.Materia materia) {
        materiaJpa.create(materia);
    }
    
    public void eliminarMateria(int id) {
        try {
            materiaJpa.destroy(id);
        } catch (com.mycompany.jpaprueba.persistencia.exceptions.NonexistentEntityException ex) {
            java.util.logging.Logger.getLogger(ControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public void editarMateria(com.mycompany.jpaprueba.logica.Materia materia) {
        try {
            materiaJpa.edit(materia);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(ControladoraPersistencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    public com.mycompany.jpaprueba.logica.Materia traerMateria(int id) {
        return materiaJpa.findMateria(id);
    }
    
    public java.util.ArrayList<com.mycompany.jpaprueba.logica.Materia> traerListaMaterias() {
        java.util.List<com.mycompany.jpaprueba.logica.Materia> lista = materiaJpa.findMateriaEntities();
        return new java.util.ArrayList<>(lista);
    }
}
