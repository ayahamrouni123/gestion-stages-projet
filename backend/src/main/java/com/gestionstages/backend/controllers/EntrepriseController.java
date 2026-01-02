package com.gestionstages.backend.controllers;

import com.gestionstages.backend.models.Entreprise;
import com.gestionstages.backend.services.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseController {

    @Autowired
    private EntrepriseService entrepriseService;

    @GetMapping
    @PreAuthorize("hasRole('ETUDIANT') or hasRole('ENCADRANT') or hasRole('ADMIN')")
    public List<Entreprise> getAllEntreprises() {
        return entrepriseService.getAllEntreprises();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ETUDIANT') or hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Entreprise> getEntrepriseById(@PathVariable Long id) {
        Entreprise entreprise = entrepriseService.getEntrepriseById(id);
        if (entreprise != null) {
            return ResponseEntity.ok(entreprise);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ENCADRANT') or hasRole('ADMIN')")
    public Entreprise createEntreprise(@RequestBody Entreprise entreprise) {
        return entrepriseService.createEntreprise(entreprise);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Entreprise> updateEntreprise(@PathVariable Long id, @RequestBody Entreprise entreprise) {
        Entreprise updatedEntreprise = entrepriseService.updateEntreprise(id, entreprise);
        if (updatedEntreprise != null) {
            return ResponseEntity.ok(updatedEntreprise);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEntreprise(@PathVariable Long id) {
        entrepriseService.deleteEntreprise(id);
        return ResponseEntity.noContent().build();
    }
}