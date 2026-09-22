package com.ejercicio.gestionproductos.models;

import jakarta.persistence.*;

/**
 * Entidad abstracta Producto.
 * 
 * Demuestra HERENCIA (junto con ProductoFisico y ProductoDigital).
 * Utilizaremos SINGLE_TABLE para mapear todas las propiedades de productos a una sola tabla.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_producto")
public abstract class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String nombre;
    private String descripcion;
    private float precioVenta;
    private boolean activo = true; // Para la baja lógica

    // Relación con Categoría (Lado inverso de la Agregación)
    @ManyToOne
    @JoinColumn(name = "categoria_id") // Clave foránea
    private Categoria categoria;

    public Producto() {}

    public abstract float calcularPrecioFinal();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public float getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(float precioVenta) { this.precioVenta = precioVenta; }
    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}

