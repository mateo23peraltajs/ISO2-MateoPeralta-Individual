package com.example.demo.repositories;

import com.example.demo.models.entities.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

// Capa: Acceso a Datos
@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    Optional<Persona> findByDocumento(String documento);
    /**
     * Búsqueda por DNI.
     */

    Optional<Persona> findByCorreoPersonal(String correoPersonal);
        /**
     * Búsqueda por correo personal de contacto.
     */
}