package com.gestionstages.backend.controllers;

import com.gestionstages.backend.models.Encadrant;
import com.gestionstages.backend.services.EncadrantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/encadrants")
public class EncadrantController {

    @Autowired
    private EncadrantService encadrantService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Encadrant> getAllEncadrants() {
        return encadrantService.getAllEncadrants();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Encadrant> getEncadrantById(@PathVariable Long id) {
        Encadrant encadrant = encadrantService.getEncadrantById(id);
        if (encadrant != null) {
            return ResponseEntity.ok(encadrant);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Encadrant createEncadrant(@RequestBody Encadrant encadrant) {
        return encadrantService.createEncadrant(encadrant);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ENCADRANT') or hasRole('ADMIN')")
    public ResponseEntity<Encadrant> updateEncadrant(@PathVariable Long id, @RequestBody Encadrant encadrant) {
        Encadrant updatedEncadrant = encadrantService.updateEncadrant(id, encadrant);
        if (updatedEncadrant != null) {
            return ResponseEntity.ok(updatedEncadrant);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteEncadrant(@PathVariable Long id) {
        encadrantService.deleteEncadrant(id);
        return ResponseEntity.noContent().build();
    }
}