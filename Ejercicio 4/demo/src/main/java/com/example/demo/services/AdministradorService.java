package com.example.demo.services;

import java.util.Map;

// Capa: Lógica de Negocio (Operaciones exclusivas de Administrador)
public interface AdministradorService {

    /**
     * Implementa la funcionalidad 'generarReporteSeguridad()' del UML
     * devolviendo un consolidado estructurado de métricas de seguridad.
     */
    Map<String, Object> generarReporteSeguridad();
}