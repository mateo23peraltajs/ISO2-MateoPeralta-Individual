package com.ejercicio.gestionproductos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Entidad Administrador que hereda de Usuario.
 * 
 * @Entity: JPA la reconocerá como parte de la jerarquía.
 * @DiscriminatorValue: El valor que tomará la columna 'rol' en la tabla 'usuario' para diferenciar a los administradores.
 */
@Entity
@DiscriminatorValue("ADMINISTRADOR")
public class Administrador extends Usuario {
    
    // Método específico del Administrador (lógica de negocio)
    public void gestionarUsuarios() {
        // Implementación de gestión
    }
}

