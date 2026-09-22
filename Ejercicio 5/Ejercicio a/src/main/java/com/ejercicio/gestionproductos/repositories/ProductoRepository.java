package com.ejercicio.gestionproductos.repositories;

import com.ejercicio.gestionproductos.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    // Buscar solo productos activos (para evitar mostrar productos dados de baja lógica)
    List<Producto> findByActivoTrue();
}

