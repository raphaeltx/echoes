package com.raph.resistance_echoes.repository;

import com.raph.resistance_echoes.model.entity.EchoesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing EchoesEntity instances.
 * Extends JpaRepository to provide CRUD operations and query methods.
 */
public interface EchoesRepository extends JpaRepository<EchoesEntity, Integer> {
}
