package com.todocodeacademy.sesiones.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {

    private Map<String, String> usuarios = new HashMap<>();

    public LoginController() {

        usuarios.put("admin", "admin123");
        usuarios.put("juanperez", "claveSuperSecreta");
        usuarios.put("usuario", "password");
        usuarios.put("invitado", "hola123");
    }

    //Login
    @GetMapping("/login")
    public String mostrarLogin(){
        return "login"; // Se redirige 
    }

    // Inicio de sesión, luego del envio de los datos
    @PostMapping("/login")
    public String procesarLogin(@RequestParam String nombreUsuario,@RequestParam String password,HttpSession session, Model model) {

        if (usuarios.containsKey(nombreUsuario)) // Si existe la clave del usuario en el mapa de usuarios
        {
            //validar la contraseña
            String contra = usuarios.get(nombreUsuario); //Devuelve el valor de la clave del usuario, en este caso la contraseña
            if (contra.equals(password)) {
                session.setAttribute("usuarioLogueado", nombreUsuario); //Guardo en la sesion al usuario, en usuarioLogueado y redirijo
                return "redirect:/bienvenida";
            }
        }

        //Mensaje de error
        model.addAttribute("error", "Usuario o contraseña erróneo");

        return "login";

    }

    //Página de Bienvenida
    @GetMapping("/bienvenida")
    public String mostrarBienvenida (HttpSession session) {

        String usuario = (String) session.getAttribute("usuarioLogueado");

        if (usuario == null) {
            return "redirect:/login";
        }

        return "bienvenida";

    }

    //Cerrar Sesión
    @GetMapping("/logout")
    public String cerrarSesion(HttpSession session) {

        session.invalidate();
        return "redirect:/login";
    }




}
