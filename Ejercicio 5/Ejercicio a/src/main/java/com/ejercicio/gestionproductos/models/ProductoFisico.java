package com.ejercicio.gestionproductos.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("FISICO")
public class ProductoFisico extends Producto {

    private int stock;
    private int stockMinimo;
    private float peso;
    private String ubicacionDeposito;

    @Override
    public float calcularPrecioFinal() {
        // En un caso real podría incluir impuestos por producto físico, envío, etc.
        return super.getPrecioVenta();
    }

    public void controlarStockMinimo() {
        if (this.stock <= this.stockMinimo) {
            // Lógica para alertar stock bajo
        }
    }

    // Getters y Setters
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public int getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(int stockMinimo) { this.stockMinimo = stockMinimo; }
    public float getPeso() { return peso; }
    public void setPeso(float peso) { this.peso = peso; }
    public String getUbicacionDeposito() { return ubicacionDeposito; }
    public void setUbicacionDeposito(String ubicacionDeposito) { this.ubicacionDeposito = ubicacionDeposito; }
}

