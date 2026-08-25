package com.example.demo.models.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase abstracta que sirve como raíz para la jerarquía de cuentas del sistema
 * (especializada en Cliente y Administrador)
 */

/*  @Inheritance(strategy = InheritanceType.JOINED):
    la tabla base ('usuarios') contiene las columnas comunes y cada subclase ('clientes', 'administradores')
    tiene su propiatabla conectada por una Foreign Key que apunta a la Primary Key de esta tabla. */
@Entity
@Table(name = "usuarios")
@Inheritance(strategy = InheritanceType.JOINED) 
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Atributos protegidos (#) según el diagrama UML
    @Column(nullable = false, unique = true, length = 100)
    protected String correo;

    @Column(nullable = false, length = 255)
    protected String clave;

    @Column(name = "intentos_fallidos", nullable = false)
    protected int intentosFallidos;

    @Column(nullable = false)
    protected boolean bloqueado;

    /** cascade = CascadeType.ALL asegura que persistir o borrar un Usuario persista 
       o borre en cascada su HistorialLogin asociado */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "historial_login_id", referencedColumnName = "id_historial")
    protected HistorialLogin historialLogin;

    /* Agregación Muchos a Muchos con GrupoDeTrabajo*/
    @ManyToMany(mappedBy = "grupo", fetch = FetchType.LAZY)
    protected List<GrupoDeTrabajo> gruposDeTrabajo = new ArrayList<>();

    // Constructores
    public Usuario() {
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.historialLogin = new HistorialLogin(this);
    }

    public Usuario(String correo, String clave) {
        this.correo = correo;
        this.clave = clave;
        this.intentosFallidos = 0;
        this.bloqueado = false;
        this.historialLogin = new HistorialLogin(this);
    }

         // Metodos de negocio
    
     // Verifica si la clave suministrada coincide con la registrada.
    public boolean autenticar(String clavePlana) {
        if (this.bloqueado) {
            return false;
        }
        return this.clave != null && this.clave.equals(clavePlana);
    }

    public void incrementarIntentos() {
        this.intentosFallidos++;
        if (this.historialLogin != null) {
            this.historialLogin.registrarIntento(false);
        }
    }

    public void resetearIntentos() {
        this.intentosFallidos = 0;
        if (this.historialLogin != null) {
            this.historialLogin.registrarIntento(true);
        }
    }

    public void bloquear() {
        this.bloqueado = true;
    }

    public void desbloquear() {
        this.bloqueado = false;
        this.intentosFallidos = 0;
    }

    public boolean isBloqueado() {
        return this.bloqueado;
    }

    public String getCorreo() {
        return this.correo;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public HistorialLogin getHistorialLogin() {
        return historialLogin;
    }

    public void setHistorialLogin(HistorialLogin historialLogin) {
        this.historialLogin = historialLogin;
    }

    public List<GrupoDeTrabajo> getGruposDeTrabajo() {
        return gruposDeTrabajo;
    }

    public void setGruposDeTrabajo(List<GrupoDeTrabajo> gruposDeTrabajo) {
        this.gruposDeTrabajo = gruposDeTrabajo;
    }
}