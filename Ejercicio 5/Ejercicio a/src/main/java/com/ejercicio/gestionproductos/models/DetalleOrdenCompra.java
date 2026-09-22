package com.ejercicio.gestionproductos.models;

import jakarta.persistence.*;

/**
 * Entidad DetalleOrdenCompra.
 * Parte de la Composición con OrdenDeCompra.
 */
@Entity
public class DetalleOrdenCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int cantidad;
    private float precioUnitario;
    private float subtotal;

    // Asociación con Producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    // Relación inversa de Composición
    @ManyToOne
    @JoinColumn(name = "orden_id", nullable = false)
    private OrdenDeCompra ordenDeCompra;

    public float calcularSubtotal() {
        this.subtotal = this.cantidad * this.precioUnitario;
        return this.subtotal;
    }

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public float getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(float precioUnitario) { this.precioUnitario = precioUnitario; }
    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }
    public OrdenDeCompra getOrdenDeCompra() { return ordenDeCompra; }
    public void setOrdenDeCompra(OrdenDeCompra ordenDeCompra) { this.ordenDeCompra = ordenDeCompra; }
}

