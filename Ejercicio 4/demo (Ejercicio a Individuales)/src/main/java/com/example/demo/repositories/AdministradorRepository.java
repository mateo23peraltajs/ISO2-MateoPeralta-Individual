package com.example.demo.repositories;

import com.example.demo.models.entities.Administrador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

// Capa: Acceso a Datos (Especializada en la entidad Administrador)
@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {

    List<Administrador> findByNivelAcceso(int nivelAcceso);

    /**
     * Filtra administradores por su nivel de privilegios en el sistema.
     */ 
}