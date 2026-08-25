package com.example.demo.config;

import com.example.demo.models.entities.Administrador;
import com.example.demo.models.entities.Cliente;
import com.example.demo.models.entities.GrupoDeTrabajo;
import com.example.demo.models.entities.Persona;
import com.example.demo.repositories.GrupoDeTrabajoRepository;
import com.example.demo.repositories.PersonaRepository;
import com.example.demo.repositories.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

// Capa: Configuración / Inicialización
// @Component registra esta clase como bean administrado
// CommandLineRunner ejecuta el método run() automáticamente luego de inicializar el contexto de Spring
@Component
public class DataInitializer implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;
    private final PersonaRepository personaRepository;
    private final GrupoDeTrabajoRepository grupoRepository;

    public DataInitializer(UsuarioRepository usuarioRepository, 
                           PersonaRepository personaRepository, 
                           GrupoDeTrabajoRepository grupoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.personaRepository = personaRepository;
        this.grupoRepository = grupoRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Solo inicializa datos si la base de datos se encuentra vacía.
        if (usuarioRepository.count() == 0) {
            
            // 1. Creación de Administrador
            Administrador admin = new Administrador("admin@empresa.com", "1234", 3);
            usuarioRepository.save(admin);

            Persona personaAdmin = new Persona("Carlos", "Gómez", "30111222", 
                    LocalDate.of(1985, 5, 12), "carlos.gomez@mail.com");
            personaAdmin.setUsuario(admin);
            personaRepository.save(personaAdmin);

            // 2. Creación de Clientes
            Cliente cliente1 = new Cliente("juan.perez@gmail.com", "1234", 650);
            usuarioRepository.save(cliente1);

            Persona personaCliente1 = new Persona("Juan", "Pérez", "40999888", 
                    LocalDate.of(1998, 10, 20), "juan.perez@mail.com");
            personaCliente1.setUsuario(cliente1);
            personaRepository.save(personaCliente1);

            Cliente cliente2 = new Cliente("ana.lopez@gmail.com", "1234", 1200);
            usuarioRepository.save(cliente2);

            Persona personaCliente2 = new Persona("Ana", "López", "38444555", 
                    LocalDate.of(1994, 3, 15), "ana.lopez@mail.com");
            personaCliente2.setUsuario(cliente2);
            personaRepository.save(personaCliente2);

            // 3. Creación de Grupo de Trabajo (Agregación)
            GrupoDeTrabajo grupoSeguridad = new GrupoDeTrabajo("Equipo de Ciberseguridad");
            grupoSeguridad.agregarUsuario(admin);
            grupoSeguridad.agregarUsuario(cliente1);
            grupoRepository.save(grupoSeguridad);
        }
    }
}