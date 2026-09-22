package com.example.demo.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

// Capa: Modelo (Entidad persistente administrada por JPA)
@Entity
// Define la tabla relacional específica para los datos del cliente
@Table(name = "clientes")
// Especifica la clave foránea que vincula esta tabla con la Primary Key de la tabla 'usuarios'
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Cliente extends Usuario {

    // Mapea la columna en la base de datos para almacenar los puntos acumulados
    @Column(name = "puntos_fidelidad", nullable = false)
    private int puntosFidelidad;

    public Cliente() {
        super();
        this.puntosFidelidad = 0;
    }

    public Cliente(String correo, String clave, int puntosFidelidad) {
        super(correo, clave);
        this.puntosFidelidad = puntosFidelidad;
    }

    // Consulta y evalúa la categoría o beneficio comercial según los puntos acumulados.
    
    public void consultarBeneficios() {
        // Lógica de negocio a nivel de entidad de dominio
        if (this.puntosFidelidad >= 1000) {
            System.out.println("Cliente Premium: 20% de descuento en servicios.");
        } else if (this.puntosFidelidad >= 500) {
            System.out.println("Cliente Gold: 10% de descuento en servicios.");
        } else {
            System.out.println("Cliente Regular: Sin descuentos acumulados.");
        }
    }

    public int getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(int puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }
}