package com.ejercicio.gestionproductos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) para Productos.
 * Se utiliza para transferir datos entre la Capa de Vista (Controlador) y la Capa de Negocio (Servicio).
 * Desacopla la entidad JPA de la información requerida/enviada por las interfaces.
 */
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El código es obligatorio")
    private String codigo;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Float precioVenta;

    @NotBlank(message = "Debe especificar el tipo de producto")
    private String tipoProducto; // FISICO o DIGITAL

    private Long categoriaId;

    // Atributos específicos
    private Integer stock;
    private Integer stockMinimo;
    private Float peso;
    private String ubicacionDeposito;

    private String urlDescarga;
    private Integer vigenciaDias;

    public ProductoDTO() {}

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public Float getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(Float precioVenta) { this.precioVenta = precioVenta; }
    public String getTipoProducto() { return tipoProducto; }
    public void setTipoProducto(String tipoProducto) { this.tipoProducto = tipoProducto; }
    public Long getCategoriaId() { return categoriaId; }
    public void setCategoriaId(Long categoriaId) { this.categoriaId = categoriaId; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }
    public Float getPeso() { return peso; }
    public void setPeso(Float peso) { this.peso = peso; }
    public String getUbicacionDeposito() { return ubicacionDeposito; }
    public void setUbicacionDeposito(String ubicacionDeposito) { this.ubicacionDeposito = ubicacionDeposito; }
    public String getUrlDescarga() { return urlDescarga; }
    public void setUrlDescarga(String urlDescarga) { this.urlDescarga = urlDescarga; }
    public Integer getVigenciaDias() { return vigenciaDias; }
    public void setVigenciaDias(Integer vigenciaDias) { this.vigenciaDias = vigenciaDias; }
}

