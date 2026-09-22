package com.example.demo.services.impl;

import com.example.demo.repositories.AdministradorRepository;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.AdministradorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

// Capa: Lógica de Negocio
@Service
public class AdministradorServiceImpl implements AdministradorService {

    private final UsuarioRepository usuarioRepository;
    private final AdministradorRepository administradorRepository;

    public AdministradorServiceImpl(UsuarioRepository usuarioRepository, AdministradorRepository administradorRepository) {
        this.usuarioRepository = usuarioRepository;
        this.administradorRepository = administradorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Map<String, Object> generarReporteSeguridad() {
        Map<String, Object> reporte = new HashMap<>();

        long totalUsuarios = usuarioRepository.count();
        long usuariosBloqueados = usuarioRepository.findByBloqueado(true).size();
        long totalAdmins = administradorRepository.count();

        reporte.put("totalUsuarios", totalUsuarios);
        reporte.put("usuariosBloqueados", usuariosBloqueados);
        reporte.put("usuariosActivos", totalUsuarios - usuariosBloqueados);
        reporte.put("totalAdministradores", totalAdmins);
        reporte.put("tasaBloqueo", totalUsuarios > 0 ? ((double) usuariosBloqueados / totalUsuarios) * 100 : 0);

        return reporte;
    }
}