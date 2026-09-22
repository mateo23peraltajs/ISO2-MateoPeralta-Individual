package com.example.demo.controllers;

import com.example.demo.services.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Capa: Controlador MVC 
// @Controller indica a Spring que esta clase procesa peticiones web y retorna nombres de plantillas HTML
@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    // Inyección de dependencias por constructor del servicio de negocio
    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    /**
     * @GetMapping("/login"): Mapea solicitudes HTTP GET para mostrar la pantalla de inicio de sesión.
     */
    @GetMapping({"/", "/login"})
    public String mostrarLogin() {
        // Retorna el archivo login.html ubicado en src/main/resources/templates/
        return "login";
    }

    /**
     * @PostMapping("/login"): Procesa las credenciales enviadas desde el formulario HTML.
     * @RequestParam: Extrae los parámetros enviados por el método POST del formulario.
     * Model: Permite enviar atributos dinámicos desde el backend hacia la vista Thymeleaf.
     */
    @PostMapping("/login")
    public String procesarLogin(@RequestParam("correo") String correo,
                                @RequestParam("clave") String clave,
                                Model model) {
        
        boolean autenticado = usuarioService.autenticar(correo, clave);

        if (autenticado) {
            // Si la autenticación es válida, redirige al panel de administración
            return "redirect:/admin/dashboard";
        }

        // Si falla, se verifica el estado para informar adecuadamente al usuario
        var usuarioOpt = usuarioService.buscarPorCorreo(correo);
        if (usuarioOpt.isPresent() && usuarioOpt.get().isBloqueado()) {
            model.addAttribute("error", "Cuenta bloqueada temporalmente por exceso de intentos fallidos.");
        } else {
            model.addAttribute("error", "Credenciales inválidas. Verifique su correo y clave.");
        }

        return "login";
    }
}