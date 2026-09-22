package com.ejercicio.gestionproductos.config;

import com.ejercicio.gestionproductos.models.Administrador;
import com.ejercicio.gestionproductos.models.Categoria;
import com.ejercicio.gestionproductos.repositories.CategoriaRepository;
import com.ejercicio.gestionproductos.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Inicializador de datos para poblar la base de datos en la primera ejecución.
 */
@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepo, 
                                      CategoriaRepository categoriaRepo, 
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear usuario administrador por defecto si no existe
            if (usuarioRepo.findByNombreUsuario("admin").isEmpty()) {
                Administrador admin = new Administrador();
                admin.setNombreUsuario("admin");
                admin.setPassword(passwordEncoder.encode("1234"));
                admin.setNombre("Admin");
                admin.setApellido("Sistema");
                admin.setEmail("admin@sistema.com");
                usuarioRepo.save(admin);
            }

            // Crear una categoría de ejemplo si no hay
            if (categoriaRepo.count() == 0) {
                Categoria cat = new Categoria();
                cat.setCodigo("CAT01");
                cat.setNombre("Electrónica");
                cat.setDescripcion("Productos electrónicos en general");
                categoriaRepo.save(cat);
            }
        };
    }
}

