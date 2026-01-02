package com.gestionstages.backend.repositories;

import com.gestionstages.backend.models.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {
    List<Stage> findByEtudiantId(Long etudiantId);
    List<Stage> findByEncadrantId(Long encadrantId);
    List<Stage> findByEntrepriseId(Long entrepriseId);
}