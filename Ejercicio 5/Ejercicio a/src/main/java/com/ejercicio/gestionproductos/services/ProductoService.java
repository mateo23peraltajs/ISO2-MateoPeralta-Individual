package com.ejercicio.gestionproductos.services;

import com.ejercicio.gestionproductos.dto.ProductoDTO;
import com.ejercicio.gestionproductos.models.*;
import com.ejercicio.gestionproductos.repositories.CategoriaRepository;
import com.ejercicio.gestionproductos.repositories.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Capa de Servicio para Productos.
 * Contiene la lógica de negocio (ABM - Alta, Baja, Modificación) e interactúa con los repositorios.
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todos los productos activos
    public List<ProductoDTO> listarProductos() {
        return productoRepository.findByActivoTrue().stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // Obtener un producto por ID
    public ProductoDTO obtenerPorId(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        return convertirADTO(producto);
    }

    // Guardar (Alta o Modificación)
    @Transactional
    public void guardarProducto(ProductoDTO dto) {
        Producto producto;
        
        if (dto.getId() != null) {
            producto = productoRepository.findById(dto.getId()).orElseThrow();
        } else {
            if ("FISICO".equals(dto.getTipoProducto())) {
                producto = new ProductoFisico();
            } else {
                producto = new ProductoDigital();
                ((ProductoDigital) producto).generarClaveLicencia(); // Lógica de negocio
            }
        }

        // Mapeo básico de DTO a Entidad
        producto.setCodigo(dto.getCodigo());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecioVenta(dto.getPrecioVenta());

        if (dto.getCategoriaId() != null) {
            Categoria cat = categoriaRepository.findById(dto.getCategoriaId()).orElse(null);
            producto.setCategoria(cat);
        }

        if (producto instanceof ProductoFisico pf) {
            pf.setStock(dto.getStock() != null ? dto.getStock() : 0);
            pf.setStockMinimo(dto.getStockMinimo() != null ? dto.getStockMinimo() : 0);
            pf.setPeso(dto.getPeso() != null ? dto.getPeso() : 0f);
            pf.setUbicacionDeposito(dto.getUbicacionDeposito());
        } else if (producto instanceof ProductoDigital pd) {
            pd.setUrlDescarga(dto.getUrlDescarga());
            pd.setVigenciaDias(dto.getVigenciaDias() != null ? dto.getVigenciaDias() : 0);
        }

        productoRepository.save(producto);
    }

    // Baja Lógica
    @Transactional
    public void bajaLogica(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        producto.setActivo(false); // No se elimina físicamente, solo se desactiva
        productoRepository.save(producto);
    }

    // Método utilitario para convertir Entidad a DTO
    private ProductoDTO convertirADTO(Producto p) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(p.getId());
        dto.setCodigo(p.getCodigo());
        dto.setNombre(p.getNombre());
        dto.setDescripcion(p.getDescripcion());
        dto.setPrecioVenta(p.getPrecioVenta());
        if(p.getCategoria() != null) {
            dto.setCategoriaId(p.getCategoria().getId());
        }

        if (p instanceof ProductoFisico pf) {
            dto.setTipoProducto("FISICO");
            dto.setStock(pf.getStock());
            dto.setStockMinimo(pf.getStockMinimo());
            dto.setPeso(pf.getPeso());
            dto.setUbicacionDeposito(pf.getUbicacionDeposito());
        } else if (p instanceof ProductoDigital pd) {
            dto.setTipoProducto("DIGITAL");
            dto.setUrlDescarga(pd.getUrlDescarga());
            dto.setVigenciaDias(pd.getVigenciaDias());
        }
        return dto;
    }
}

