package com.gestionstages.backend.services;

import com.gestionstages.backend.models.Encadrant;
import com.gestionstages.backend.repositories.EncadrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EncadrantService {

    @Autowired
    private EncadrantRepository encadrantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Encadrant> getAllEncadrants() {
        return encadrantRepository.findAll();
    }

    public Encadrant getEncadrantById(Long id) {
        return encadrantRepository.findById(id).orElse(null);
    }

    public Encadrant createEncadrant(Encadrant encadrant) {
        // Encoder le mot de passe
        encadrant.setPassword(passwordEncoder.encode(encadrant.getPassword()));
        return encadrantRepository.save(encadrant);
    }

    public Encadrant updateEncadrant(Long id, Encadrant encadrantDetails) {
        Encadrant encadrant = encadrantRepository.findById(id).orElse(null);
        if (encadrant != null) {
            encadrant.setNom(encadrantDetails.getNom());
            encadrant.setPrenom(encadrantDetails.getPrenom());
            encadrant.setEmail(encadrantDetails.getEmail());
            if (encadrantDetails.getPassword() != null && !encadrantDetails.getPassword().isEmpty()) {
                encadrant.setPassword(passwordEncoder.encode(encadrantDetails.getPassword()));
            }
            encadrant.setDepartement(encadrantDetails.getDepartement());
            encadrant.setTelephone(encadrantDetails.getTelephone());
            return encadrantRepository.save(encadrant);
        }
        return null;
    }

    public void deleteEncadrant(Long id) {
        encadrantRepository.deleteById(id);
    }
}