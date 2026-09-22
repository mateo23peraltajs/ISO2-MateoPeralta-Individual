package com.ejercicio.gestionproductos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Entidad EncargadoCompras que hereda de Usuario.
 */
@Entity
@DiscriminatorValue("ENCARGADO_COMPRAS")
public class EncargadoCompras extends Usuario {

    public void generarOrdenCompra() {
        // Implementación
    }

    public void recibirMercaderia() {
        // Implementación
    }
}

