package com.ejercicio.gestionproductos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Entidad Vendedor que hereda de Usuario.
 */
@Entity
@DiscriminatorValue("VENDEDOR")
public class Vendedor extends Usuario {
    
    public void registrarVenta() {
        // Implementación
    }
}

