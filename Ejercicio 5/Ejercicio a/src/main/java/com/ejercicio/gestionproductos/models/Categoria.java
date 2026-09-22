package com.ejercicio.gestionproductos.models;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad Categoria.
 * 
 * Agrupación de productos por categoría. Demuestra la relación de AGREGACIÓN.
 * Una categoría agrupa a muchos productos, pero pueden existir independientemente.
 */
@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigo;
    private String nombre;
    private String descripcion;

    // Relación de Agregación: Una categoría tiene muchos productos.
    // Usamos @OneToMany pero el ciclo de vida de Producto no depende exclusivamente de Categoria.
    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    public Categoria() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public List<Producto> getProductos() { return productos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
}

