package com.gestionstages.backend.controllers;

import com.gestionstages.backend.models.Stage;
import com.gestionstages.backend.services.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping
    @PreAuthorize("hasRole('ETUDIANT') or hasRole('ENCADRANT') or hasRole('ADMIN')")
    public List<Stage> getAllStages() {
        return stageService.getAllStages();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ETUDIANT') or hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        Stage stage = stageService.getStageById(id);
        if (stage != null) {
            return ResponseEntity.ok(stage);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ETUDIANT') or hasRole('ENCADRANT') or hasRole('ADMIN')")
    public Stage createStage(@RequestBody Stage stage) {
        return stageService.createStage(stage);
    }
    // src/main/java/com/gestionstages/backend/controllers/StageController.java

    @PutMapping("/{id}/valider")
    @PreAuthorize("hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Void> validerStage(@PathVariable Long id) {
        stageService.validerStage(id); // À implémenter dans StageService
        return ResponseEntity.ok().build();
    }


    @PutMapping("/{id}/refuser")
    @PreAuthorize("hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Void> refuserStage(@PathVariable Long id) {
        stageService.refuserStage(id); // À implémenter dans StageService
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ETUDIANT') or hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody Stage stage) {
        Stage updatedStage = stageService.updateStage(id, stage);
        if (updatedStage != null) {
            return ResponseEntity.ok(updatedStage);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Stage> getStagesByEtudiantId(@PathVariable Long etudiantId) {
        return stageService.getStagesByEtudiantId(etudiantId);
    }
}