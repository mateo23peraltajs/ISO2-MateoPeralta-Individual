package com.example.demo.services;

import com.example.demo.models.entities.Usuario;
import java.util.List;
import java.util.Optional;

// Capa: Lógica de Negocio 
public interface UsuarioService {

    /**
     * Valida credenciales, registra el intento en el historial y aplica bloqueo
     * automático si se superan los 3 intentos fallidos consecutivos.
     */
    boolean autenticar(String correo, String clave);

    Optional<Usuario> buscarPorCorreo(String correo);

    Optional<Usuario> buscarPorId(Long id);

    List<Usuario> listarTodos();

    List<Usuario> listarBloqueados();

    void bloquearUsuario(Long id);

    void desbloquearUsuario(Long id);
}