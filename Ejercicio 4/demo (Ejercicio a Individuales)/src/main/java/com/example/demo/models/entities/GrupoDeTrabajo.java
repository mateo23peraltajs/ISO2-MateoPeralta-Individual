package com.example.demo.models.entities;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

// Capa: Modelo (Entidad encargada de agrupar usuarios para control colectivo)
@Entity
// Nombre de la tabla contenedora de grupos
@Table(name = "grupos_trabajo")
public class GrupoDeTrabajo {

    // Clave primaria autoincremental de la entidad
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre identificador del grupo (no nulo, longitud máxima 100 caracteres)
    @Column(name = "nombre_grupo", nullable = false, length = 100)
    private String nombreGrupo;

    /**
     * Un grupo contiene 1 o más usuarios, y un usuario puede pertenecer a 0 o más grupos.
     * @JoinTable genera la tabla intermedia 'grupos_usuarios' con las claves foráneas de ambas tablas.
     */
    @ManyToMany(fetch = FetchType.LAZY)  //Los usuarios del grupo no se cargan automáticamente cuando cargas un grupo. Solo se cargan cuando explícitamente accedes a la lista de usuarios (ej: grupo.getGrupo()).
    @JoinTable(
        name = "grupos_usuarios",
        joinColumns = @JoinColumn(name = "grupo_id"),
        inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> grupo = new ArrayList<>();

    public GrupoDeTrabajo() {
    }

    public GrupoDeTrabajo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    /**
     * Funcionalidad requerida por el UML:
     * Agrega un usuario a la colección del grupo si no se encuentra presente.
     */
    public void agregarUsuario(Usuario u) {
        if (u != null && !this.grupo.contains(u)) {
            this.grupo.add(u);
            u.getGruposDeTrabajo().add(this);
        }
    }

    /**
     * Funcionalidad requerida por el UML:
     * Remueve a un usuario de la colección del grupo.
     */
    public void removerUsuario(Usuario u) {
        if (u != null && this.grupo.contains(u)) {
            this.grupo.remove(u);
            u.getGruposDeTrabajo().remove(this);
        }
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public List<Usuario> getGrupo() {
        return grupo;
    }

    public void setGrupo(List<Usuario> grupo) {
        this.grupo = grupo;
    }
}