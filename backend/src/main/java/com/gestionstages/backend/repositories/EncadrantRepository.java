package com.gestionstages.backend.repositories;

import com.gestionstages.backend.models.Encadrant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface EncadrantRepository extends JpaRepository<Encadrant, Long> {
    Optional<Encadrant> findByEmail(String email);
}