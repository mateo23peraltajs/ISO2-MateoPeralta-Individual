package com.example.demo.models.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Representa el registro histórico y métricas de acceso para un usuario.
 * COMPOSICIÓN (1 a 1) con Usuario.
 * Si el usuario se elimina, su historial de inicio de sesión se destruye en cascada.
 *
 * ANOTACIONES JPA UTILIZADAS:
 * - @Entity: Declara que la clase es un objeto administrado por el contexto de persistencia JPA.
 * - @Table: Especifica el nombre explícito de la tabla relacional ('historial_logins').

 * - @Column: Permite parametrizar las restricciones de cada columna (nulos, nombres de campo, etc.).
 * - @OneToOne: Define la relación de multiplicidad 1 a 1.
 * ============================================================================
 */
@Entity
@Table(name = "historial_logins")
public class HistorialLogin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Definen la clave primaria con estrategia de auto-incremento (SERIAL/IDENTITY).
    @Column(name = "id_historial") // Mapeada con esta columna de la bdd
    private Long id;

    @Column(name = "ultimo_acceso")
    private LocalDateTime ultimoAcceso;

    @Column(name = "conteo_fallos", nullable = false)
    private int conteoFallos;

    /**
     * Mapeo bidireccional hacia la entidad propietaria (Usuario).
     * 'mappedBy = "historialLogin"' indica que la clave foránea reside en la tabla de Usuario.
     */
    @OneToOne(mappedBy = "historialLogin", fetch = FetchType.LAZY)
    private Usuario usuario;

    // Constructores
    public HistorialLogin() {
        this.conteoFallos = 0;
        this.ultimoAcceso = LocalDateTime.now();
    }

    public HistorialLogin(Usuario usuario) {
        this.usuario = usuario;
        this.conteoFallos = 0;
        this.ultimoAcceso = LocalDateTime.now();
    }

    /**
     * Si el inicio de sesión es exitoso, actualiza la marca temporal del último acceso y resetea los fallos acumulados en el historial.
     * Si es fallido, incrementa el contador de intentos fallidos.
     */
    public void registrarIntento(boolean exito) {
        this.ultimoAcceso = LocalDateTime.now();
        if (exito) {
            this.conteoFallos = 0;
        } else {
            this.conteoFallos++;
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getUltimoAcceso() {
        return ultimoAcceso;
    }

    public void setUltimoAcceso(LocalDateTime ultimoAcceso) {
        this.ultimoAcceso = ultimoAcceso;
    }

    public int getConteoFallos() {
        return conteoFallos;
    }

    public void setConteoFallos(int conteoFallos) {
        this.conteoFallos = conteoFallos;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
