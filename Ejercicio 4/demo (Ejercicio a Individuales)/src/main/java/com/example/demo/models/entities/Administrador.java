package com.example.demo.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

// Capa: Modelo (Subclase de Usuario mapeada mediante herencia JOINED)
@Entity
//persistir los atributos específicos del rol administrador
@Table(name = "administradores")
// Clave foránea que referencia a la tabla 'usuarios'
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Administrador extends Usuario {

    // Nivel de privilegios (ej. 1 = Operador, 2 = Supervisor, 3 = SuperAdmin)
    @Column(name = "nivel_acceso", nullable = false)
    private int nivelAcceso;

    public Administrador() {
        super();
        this.nivelAcceso = 1;
    }

    public Administrador(String correo, String clave, int nivelAcceso) {
        super(correo, clave);
        this.nivelAcceso = nivelAcceso;
    }

    /**
     * Funcionalidad requerida por el UML:
     * Permite a un administrador con permisos suficientes restablecer el estado de bloqueo de una cuenta.
     */
    public void desbloquearUsuario(Usuario u) {
        if (u != null) {
            u.desbloquear();
        }
    }

    /**
     * Funcionalidad requerida por el UML:
     * Genera una traza de auditoría o reporte básico del estado de seguridad.
     */
    public void generarReporteSeguridad() {
        System.out.println("Reporte emitido por admin: " + this.correo + " | Nivel de acceso: " + this.nivelAcceso);
    }

    public int getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(int nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }
}