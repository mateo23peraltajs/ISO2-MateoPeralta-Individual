package com.example.demo.services.impl;

import com.example.demo.models.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.UsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// Capa: Lógica de Negocio (Implementación Concreta)
// @Service registra la clase como bean de servicio en el contenedor de inversión de control de Spring
@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    // Inyeccion de dependencias por constructor.
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * @Transactional asegura que todas las modificaciones sobre Usuario e HistorialLogin
     * se ejecuten dentro de una única transacción atómica!!! en la base de datos.
     */
    @Override
    @Transactional
    public boolean autenticar(String correo, String clave) {
        Optional<Usuario> optUsuario = usuarioRepository.findByCorreo(correo);

        if (optUsuario.isEmpty()) {
            return false;
        }

        Usuario usuario = optUsuario.get();

        // Si ya está bloqueado, no se permite procesar la autenticación
        if (usuario.isBloqueado()) {
            return false;
        }

        // Validación de coincidencia de contraseña
        boolean loginValido = usuario.autenticar(clave);

        if (loginValido) {
            // Restablece intentos fallidos a 0 y registra éxito en HistorialLogin
            usuario.resetearIntentos();
        } else {
            // Incrementa contador e historial
            usuario.incrementarIntentos();
            // Regla de negocio: Bloqueo automático al 3er fallo
            if (usuario.getIntentosFallidos() >= 3) {
                usuario.bloquear();
            }
        }

        // Hibernate sincroniza automáticamente los cambios de estado (Dirty Checking)
        usuarioRepository.save(usuario);
        return loginValido;
    }

    // @Transactional(readOnly = true) optimiza las consultas SQL en modo solo lectura
    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorCorreo(String correo) {
        return usuarioRepository.findByCorreo(correo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarBloqueados() {
        return usuarioRepository.findByBloqueado(true);
    }

    @Override
    @Transactional
    public void bloquearUsuario(Long id) {
        usuarioRepository.findById(id).ifPresent(u -> {
            u.bloquear();
            usuarioRepository.save(u);
        });
    }

    @Override
    @Transactional
    public void desbloquearUsuario(Long id) {
        usuarioRepository.findById(id).ifPresent(u -> {
            u.desbloquear();
            usuarioRepository.save(u);
        });
    }
}