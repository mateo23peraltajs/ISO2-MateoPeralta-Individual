package com.example.demo.services.impl;

import com.example.demo.models.entities.GrupoDeTrabajo;
import com.example.demo.models.entities.Usuario;
import com.example.demo.repositories.GrupoDeTrabajoRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.GrupoDeTrabajoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Capa: Lógica de Negocio
@Service
public class GrupoDeTrabajoServiceImpl implements GrupoDeTrabajoService {

    private final GrupoDeTrabajoRepository grupoRepository;
    private final UsuarioRepository usuarioRepository;

    public GrupoDeTrabajoServiceImpl(GrupoDeTrabajoRepository grupoRepository, UsuarioRepository usuarioRepository) {
        this.grupoRepository = grupoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GrupoDeTrabajo> listarGrupos() {
        return grupoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GrupoDeTrabajo> buscarPorId(Long id) {
        return grupoRepository.findById(id);
    }

    @Override
    @Transactional
    public GrupoDeTrabajo crearGrupo(String nombreGrupo) {
        GrupoDeTrabajo nuevoGrupo = new GrupoDeTrabajo(nombreGrupo);
        return grupoRepository.save(nuevoGrupo);
    }

    @Override
    @Transactional
    public void agregarUsuarioAGrupo(Long grupoId, Long usuarioId) {
        Optional<GrupoDeTrabajo> optGrupo = grupoRepository.findById(grupoId);
        Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioId);

        if (optGrupo.isPresent() && optUsuario.isPresent()) {
            GrupoDeTrabajo grupo = optGrupo.get();
            Usuario usuario = optUsuario.get();
            // Ejecuta el método de agregación modelado en el UML
            grupo.agregarUsuario(usuario);
            grupoRepository.save(grupo);
        }
    }

    @Override
    @Transactional
    public void removerUsuarioDeGrupo(Long grupoId, Long usuarioId) {
        Optional<GrupoDeTrabajo> optGrupo = grupoRepository.findById(grupoId);
        Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioId);

        if (optGrupo.isPresent() && optUsuario.isPresent()) {
            GrupoDeTrabajo grupo = optGrupo.get();
            Usuario usuario = optUsuario.get();
            // Ejecuta el método de remoción modelado en el UML
            grupo.removerUsuario(usuario);
            grupoRepository.save(grupo);
        }
    }
}