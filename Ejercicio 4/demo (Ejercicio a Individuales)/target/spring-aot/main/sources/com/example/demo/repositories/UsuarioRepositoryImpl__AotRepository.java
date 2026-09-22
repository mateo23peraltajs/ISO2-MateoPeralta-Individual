package com.example.demo.repositories;

import com.example.demo.models.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import org.springframework.aot.generate.Generated;
import org.springframework.data.jpa.repository.aot.AotRepositoryFragmentSupport;
import org.springframework.data.jpa.repository.query.QueryEnhancerSelector;
import org.springframework.data.repository.core.support.RepositoryFactoryBeanSupport;

/**
 * AOT generated JPA repository implementation for {@link UsuarioRepository}.
 */
@Generated
public class UsuarioRepositoryImpl__AotRepository extends AotRepositoryFragmentSupport {
  private final RepositoryFactoryBeanSupport.FragmentCreationContext context;

  private final EntityManager entityManager;

  public UsuarioRepositoryImpl__AotRepository(EntityManager entityManager,
      RepositoryFactoryBeanSupport.FragmentCreationContext context) {
    super(QueryEnhancerSelector.DEFAULT_SELECTOR, context);
    this.entityManager = entityManager;
    this.context = context;
  }

  /**
   * AOT generated implementation of {@link UsuarioRepository#existsByCorreo(java.lang.String)}.
   */
  public boolean existsByCorreo(String correo) {
    String queryString = "SELECT u.id FROM Usuario u WHERE u.correo = :correo";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("correo", correo);
    query.setMaxResults(1);

    return !query.getResultList().isEmpty();
  }

  /**
   * AOT generated implementation of {@link UsuarioRepository#findByBloqueado(boolean)}.
   */
  public List<Usuario> findByBloqueado(boolean bloqueado) {
    String queryString = "SELECT u FROM Usuario u WHERE u.bloqueado = :bloqueado";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("bloqueado", bloqueado);

    return (List<Usuario>) query.getResultList();
  }

  /**
   * AOT generated implementation of {@link UsuarioRepository#findByCorreo(java.lang.String)}.
   */
  public Optional<Usuario> findByCorreo(String correo) {
    String queryString = "SELECT u FROM Usuario u WHERE u.correo = :correo";
    Query query = this.entityManager.createQuery(queryString);
    query.setParameter("correo", correo);

    return Optional.ofNullable((Usuario) convertOne(query.getSingleResultOrNull(), false, Usuario.class));
  }
}
