package com.example.demo.repositories;

import com.example.demo.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Capa: Acceso a Datos (Especializada en la entidad Cliente)
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByPuntosFidelidadGreaterThanEqual(int puntos);

    /**
     * Obtiene todos los clientes que superen un determinado umbral de puntos de fidelidad.
     * Genera: SELECT * FROM clientes c JOIN usuarios u ON c.usuario_id = u.id WHERE c.puntos_fidelidad >= ?
     */
}
