package com.gestionstages.backend.repositories;

import com.gestionstages.backend.models.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Boolean existsByEmail(String email);
}