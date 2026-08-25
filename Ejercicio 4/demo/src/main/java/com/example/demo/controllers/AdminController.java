package com.example.demo.controllers;

import com.example.demo.services.AdministradorService;
import com.example.demo.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

// Capa: Controlador MVC 
// @RequestMapping("/admin"): Define una ruta base compartida por todos los endpoints de este controlador
@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UsuarioService usuarioService;
    private final AdministradorService administradorService;

    public AdminController(UsuarioService usuarioService, AdministradorService administradorService) {
        this.usuarioService = usuarioService;
        this.administradorService = administradorService;
    }

    /**
     * @GetMapping("/dashboard"): Carga el panel principal con el reporte de seguridad y la lista de usuarios.
     */
    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        // Ejecuta la funcionalidad generarReporteSeguridad() definida en el UML
        model.addAttribute("reporte", administradorService.generarReporteSeguridad());
        // Carga la lista completa de usuarios para gestión administrativa
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "admin/dashboard";
    }

    /**
     * @PostMapping("/desbloquear/{id}"): Implementa la funcionalidad desbloquearUsuario(u) del UML.
     * @PathVariable: Captura el ID de usuario directamente desde la URL.
     */
    @PostMapping("/desbloquear/{id}")
    public String desbloquearUsuario(@PathVariable("id") Long id) {
        usuarioService.desbloquearUsuario(id);
        return "redirect:/admin/dashboard";
    }

    /**
     * @PostMapping("/bloquear/{id}"): Permite forzar el bloqueo manual de una cuenta.
     */
    @PostMapping("/bloquear/{id}")
    public String bloquearUsuario(@PathVariable("id") Long id) {
        usuarioService.bloquearUsuario(id);
        return "redirect:/admin/dashboard";
    }
}