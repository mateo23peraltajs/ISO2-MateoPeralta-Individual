package com.ejercicio.gestionproductos.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

/**
 * Entidad OrdenDeCompra.
 * Demuestra ASOCIACIÓN simple (con Proveedor y EncargadoCompras) y
 * COMPOSICIÓN (con DetalleOrdenCompra).
 */
@Entity
public class OrdenDeCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int numero;
    
    @Temporal(TemporalType.DATE)
    private Date fechaEmision;

    @Temporal(TemporalType.DATE)
    private Date fechaEntregaEstimada;
    
    private String estado;
    private float total;

    // Relación de Asociación (0..* a 1)
    // Una orden pertenece a un proveedor
    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;

    // Relación de Asociación (0..* a 1)
    // Una orden es registrada por un encargado de compras
    @ManyToOne
    @JoinColumn(name = "encargado_id")
    private EncargadoCompras encargadoCompras;

    // Relación de Composición (1 a 1..*)
    // El detalle de la orden no tiene sentido si la orden se elimina.
    // Usamos cascade = CascadeType.ALL y orphanRemoval = true para asegurar el comportamiento de Composición.
    @OneToMany(mappedBy = "ordenDeCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleOrdenCompra> detalles = new ArrayList<>();

    public void agregarDetalle(DetalleOrdenCompra detalle) {
        detalles.add(detalle);
        detalle.setOrdenDeCompra(this);
    }

    public void calcularTotal() {
        this.total = 0;
        if(detalles != null) {
            for(DetalleOrdenCompra det : detalles) {
                this.total += det.calcularSubtotal();
            }
        }
    }

    public void confirmarRecepcion() {
        this.estado = "RECIBIDA";
    }

    // Getters y Setters omitidos por brevedad, salvo los clave.
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }
    public Proveedor getProveedor() { return proveedor; }
    public void setProveedor(Proveedor proveedor) { this.proveedor = proveedor; }
    public List<DetalleOrdenCompra> getDetalles() { return detalles; }
    public void setDetalles(List<DetalleOrdenCompra> detalles) { this.detalles = detalles; }
}

