package com.gestionstages.backend.services;

import com.gestionstages.backend.models.Stage;
import com.gestionstages.backend.repositories.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Stage getStageById(Long id) {
        return stageRepository.findById(id).orElse(null);
    }

    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Stage updateStage(Long id, Stage stageDetails) {
        Stage stage = stageRepository.findById(id).orElse(null);
        if (stage != null) {
            stage.setTitre(stageDetails.getTitre());
            stage.setDescription(stageDetails.getDescription());
            stage.setDateDebut(stageDetails.getDateDebut());
            stage.setDateFin(stageDetails.getDateFin());
            stage.setStatut(stageDetails.getStatut());
            stage.setRapportUrl(stageDetails.getRapportUrl());

            if (stageDetails.getEtudiant() != null) {
                stage.setEtudiant(stageDetails.getEtudiant());
            }
            if (stageDetails.getEncadrant() != null) {
                stage.setEncadrant(stageDetails.getEncadrant());
            }
            if (stageDetails.getEntreprise() != null) {
                stage.setEntreprise(stageDetails.getEntreprise());
            }

            return stageRepository.save(stage);
        }
        return null;
    }
    public void validerStage(Long id) {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage non trouvé"));
        stage.setStatut("VALIDE");
        stageRepository.save(stage);
    }

    public void refuserStage(Long id) {
        Stage stage = stageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stage non trouvé"));
        stage.setStatut("REFUSE");
        stageRepository.save(stage);
    }

    public void deleteStage(Long id) {
        stageRepository.deleteById(id);
    }

    public List<Stage> getStagesByEtudiantId(Long etudiantId) {
        return stageRepository.findByEtudiantId(etudiantId);
    }
}