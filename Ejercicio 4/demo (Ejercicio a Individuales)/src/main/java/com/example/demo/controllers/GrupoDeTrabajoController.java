package com.example.demo.controllers;

import com.example.demo.services.GrupoDeTrabajoService;
import com.example.demo.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Capa: Controlador MVC (Manejo de la agregación GrupoDeTrabajo - Usuario)
@Controller
@RequestMapping("/grupos")
public class GrupoDeTrabajoController {

    private final GrupoDeTrabajoService grupoService;
    private final UsuarioService usuarioService;

    public GrupoDeTrabajoController(GrupoDeTrabajoService grupoService, UsuarioService usuarioService) {
        this.grupoService = grupoService;
        this.usuarioService = usuarioService;
    }

    /**
     * Muestra el listado de grupos de trabajo y todos los usuarios disponibles para asignar.
     */
    @GetMapping
    public String listarGrupos(Model model) {
        model.addAttribute("grupos", grupoService.listarGrupos());
        model.addAttribute("usuarios", usuarioService.listarTodos());
        return "grupos/lista";
    }

    /**
     * Procesa la creación de un nuevo grupo de trabajo.
     */
    @PostMapping("/crear")
    public String crearGrupo(@RequestParam("nombreGrupo") String nombreGrupo) {
        grupoService.crearGrupo(nombreGrupo);
        return "redirect:/grupos";
    }

    /**
     * Ejecuta el método agregarUsuario(u) del diagrama UML.
     */
    @PostMapping("/{grupoId}/agregar-usuario")
    public String agregarUsuario(@PathVariable("grupoId") Long grupoId,
                                 @RequestParam("usuarioId") Long usuarioId) {
        grupoService.agregarUsuarioAGrupo(grupoId, usuarioId);
        return "redirect:/grupos";
    }

    /**
     * Ejecuta el método removerUsuario(u) del diagrama UML.
     */
    @PostMapping("/{grupoId}/remover-usuario/{usuarioId}")
    public String removerUsuario(@PathVariable("grupoId") Long grupoId,
                                 @PathVariable("usuarioId") Long usuarioId) {
        grupoService.removerUsuarioDeGrupo(grupoId, usuarioId);
        return "redirect:/grupos";
    }
}