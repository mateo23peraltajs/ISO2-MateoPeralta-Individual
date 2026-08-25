package com.example.demo.repositories;

import com.example.demo.models.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

// Capa: Acceso a Datos (Spring Data JPA)
// @Repository indica a Spring que esta interfaz es un componente de persistencia y traduce excepciones SQL a DataAccessException
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     * Query Method derivado:
     * Genera automáticamente la consulta: SELECT * FROM usuarios WHERE correo = ?
     * Retorna un Optional para manejar de forma segura la presencia o ausencia del registro.
     */
    Optional<Usuario> findByCorreo(String correo);

    /**
    En Spring Data JPA no necesitás programar la implementación 
    ni escribir sentencias SQL manuales para operaciones comunes;

    findBy: Traduce a SELECT * FROM usuarios WHERE.
    Correo: Busca el atributo private String correo dentro de la entidad Usuario.
    (String correo): Pasa el argumento como parámetro seguro (?) en la consulta preparada.
     * Comprueba si ya existe un usuario registrado con el correo especificado.
     */
    
    boolean existsByCorreo(String correo);

    /**
     * Obtiene el listado de usuarios filtrando por su estado de bloqueo.
     * Genera: SELECT * FROM usuarios WHERE bloqueado = ?
     */
    List<Usuario> findByBloqueado(boolean bloqueado);
}