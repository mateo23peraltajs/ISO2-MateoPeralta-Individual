package com.ejercicio.gestionproductos.controllers;

import com.ejercicio.gestionproductos.dto.ProductoDTO;
import com.ejercicio.gestionproductos.services.ProductoService;
import com.ejercicio.gestionproductos.repositories.CategoriaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador MVC para la gestión de Productos (ABM).
 * 
 * Anotaciones:
 * @Controller: Indica que la clase es un controlador MVC y devolverá vistas (archivos HTML).
 * @RequestMapping: Mapea todas las URLs que empiecen con /productos a este controlador.
 */
@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Muestra el listado de productos.
     */
    @GetMapping
    public String listarProductos(Model model) {
        // Model se usa para pasar datos del Controlador a la Vista (Thymeleaf)
        model.addAttribute("productos", productoService.listarProductos());
        return "productos/lista"; // Retorna la vista lista.html en la carpeta productos
    }

    /**
     * Muestra el formulario para crear un NUEVO producto (Alta).
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioAlta(Model model) {
        model.addAttribute("productoDTO", new ProductoDTO());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "productos/formulario";
    }

    /**
     * Muestra el formulario para EDITAR un producto existente (Modificación).
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        ProductoDTO dto = productoService.obtenerPorId(id);
        model.addAttribute("productoDTO", dto);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "productos/formulario";
    }

    /**
     * Procesa el envío del formulario (tanto para Alta como para Modificación).
     * @Valid: Ejecuta las validaciones definidas en el DTO (@NotBlank, @NotNull, etc.).
     * BindingResult: Contiene el resultado de las validaciones.
     */
    @PostMapping("/guardar")
    public String guardarProducto(@Valid @ModelAttribute("productoDTO") ProductoDTO productoDTO, 
                                  BindingResult result, Model model) {
        if (result.hasErrors()) {
            // Si hay errores, volvemos a mostrar el formulario
            model.addAttribute("categorias", categoriaRepository.findAll());
            return "productos/formulario";
        }

        productoService.guardarProducto(productoDTO);
        return "redirect:/productos"; // Redirección al listado tras guardar exitosamente
    }

    /**
     * Realiza la baja (lógica) de un producto.
     */
    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Long id) {
        productoService.bajaLogica(id);
        return "redirect:/productos";
    }
}

