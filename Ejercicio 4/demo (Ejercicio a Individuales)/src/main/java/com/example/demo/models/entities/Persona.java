package com.example.demo.models.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * CAPA: Modelo (Entidad JPA)
 * DESCRIPCIÓN: Representa la información personal y civil de una persona física.
 * Según el diagrama UML, mantiene una relación de asociación 1 a 1 con la clase Usuario ("posee cuenta").
 */

@Entity // Mapeo de entidad
@Table(name = "personas") // Se mapea a la tabla personas de la tabla db_seguridad_tp
public class Persona {

    /**
     * Identificador único autoincremental para persistencia en base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 60)
    private String nombre;

    @Column(nullable = false, length = 60)
    private String apellido;

    @Column(nullable = false, unique = true, length = 20)
    private String documento;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "correo_personal", nullable = false, unique = true, length = 100)
    private String correoPersonal;

    /**
     * ANOTACIÓN: @OneToOne
     * Relación 1 a 1 unidireccional o bidireccional con Usuario.
     * JoinColumn define la clave foránea (usuario_id) en la tabla 'personas'.
     */

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = true) // Crea la clave foránea para enlazar a la persona con su cuenta de acceso (Usuario).
    private Usuario usuario;

    // Constructores
    public Persona() {
    }

    public Persona(String nombre, String apellido, String documento, LocalDate fechaNacimiento, String correoPersonal) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
        this.correoPersonal = correoPersonal;
    }

    // Métodos de negocio definidos en el UML
    public String getCorreoPersonal() {
        return this.correoPersonal;
    }

    public String getDatosCompletos() {
        return this.nombre + " " + this.apellido + " - DNI: " + this.documento;
    }

    // Getters y Setters estándar
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCorreoPersonal(String correoPersonal) {
        this.correoPersonal = correoPersonal;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}