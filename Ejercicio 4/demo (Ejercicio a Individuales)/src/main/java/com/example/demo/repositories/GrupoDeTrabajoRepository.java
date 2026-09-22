package com.example.demo.repositories;

import com.example.demo.models.entities.GrupoDeTrabajo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Capa: Acceso a Datos
@Repository
public interface GrupoDeTrabajoRepository extends JpaRepository<GrupoDeTrabajo, Long> {

    /**
     * Busca un grupo por su nombre exacto.
     */
    Optional<GrupoDeTrabajo> findByNombreGrupo(String nombreGrupo);

    /**
     * Búsqueda parcial que ignora mayúsculas y minúsculas (para filtros y buscadores).
     * Genera: SELECT * FROM grupos_trabajo WHERE UPPER(nombre_grupo) LIKE UPPER('%term%')
     */
    List<GrupoDeTrabajo> findByNombreGrupoContainingIgnoreCase(String nombreGrupo);
}