package com.example.demo.services;

import com.example.demo.models.entities.GrupoDeTrabajo;
import java.util.List;
import java.util.Optional;

// Capa: Lógica de Negocio (Gestión de Agregación de Grupos y Usuarios)
public interface GrupoDeTrabajoService {

    List<GrupoDeTrabajo> listarGrupos();

    Optional<GrupoDeTrabajo> buscarPorId(Long id);

    GrupoDeTrabajo crearGrupo(String nombreGrupo);

    void agregarUsuarioAGrupo(Long grupoId, Long usuarioId);

    void removerUsuarioDeGrupo(Long grupoId, Long usuarioId);
}