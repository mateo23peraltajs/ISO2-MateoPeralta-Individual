package com.ejercicio.gestionproductos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DIGITAL")
public class ProductoDigital extends Producto {

    private String claveLicencia;
    private String urlDescarga;
    private int vigenciaDias;

    @Override
    public float calcularPrecioFinal() {
        // Por ejemplo, productos digitales no tienen costo de envío
        return super.getPrecioVenta();
    }

    public void generarClaveLicencia() {
        // Lógica de generación de clave
        this.claveLicencia = java.util.UUID.randomUUID().toString();
    }

    // Getters y Setters
    public String getClaveLicencia() { return claveLicencia; }
    public void setClaveLicencia(String claveLicencia) { this.claveLicencia = claveLicencia; }
    public String getUrlDescarga() { return urlDescarga; }
    public void setUrlDescarga(String urlDescarga) { this.urlDescarga = urlDescarga; }
    public int getVigenciaDias() { return vigenciaDias; }
    public void setVigenciaDias(int vigenciaDias) { this.vigenciaDias = vigenciaDias; }
}

