package com.ejercicio.gestionproductos.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * Entidad abstracta Usuario que representa a los usuarios del sistema.
 * Implementa UserDetails para la integración con Spring Security (Login).
 * 
 * Anotaciones importantes:
 * @Entity: Indica que esta clase es una entidad JPA y se mapeará a una tabla de base de datos.
 * @Inheritance: Define la estrategia de herencia. SINGLE_TABLE crea una única tabla para todas las subclases.
 * @DiscriminatorColumn: La columna que diferenciará qué subclase es cada registro (ej: 'ADMIN', 'VENDEDOR').
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "rol", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String nombreUsuario; // Credencial para login

    @Column(nullable = false)
    private String password; // Credencial para login (encriptada)

    private String nombre;
    private String apellido;
    private String email;

    // Métodos de Spring Security (UserDetails)
    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // En SINGLE_TABLE el tipo concreto (y por ende el rol) está determinado por la clase hija
        // Aquí podríamos mapear el nombre de la clase a un Rol, pero Spring permite manejarlo de varias formas.
        // Lo simplificaremos devolviendo el nombre de la clase como rol base.
        return List.of(new SimpleGrantedAuthority("ROLE_" + this.getClass().getSimpleName().toUpperCase()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

    // Getters y Setters tradicionales
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }
    public void setPassword(String password) { this.password = password; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}

