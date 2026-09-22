package com.todocodeacademy.PruebaThymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SaludoController {

    @GetMapping("/saludo")
    public String saludo(@RequestParam String nombre, //Ahora va a recibir un parametro llamado nombre, y lo va a exigir antes
                         Model model) {
        model.addAttribute("nombre", nombre);
        return "saludo";

    }
}
