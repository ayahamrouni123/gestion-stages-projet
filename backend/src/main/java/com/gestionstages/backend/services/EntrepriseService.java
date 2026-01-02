package com.gestionstages.backend.services;

import com.gestionstages.backend.models.Entreprise;
import com.gestionstages.backend.repositories.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    public List<Entreprise> getAllEntreprises() {
        return entrepriseRepository.findAll();
    }

    public Entreprise getEntrepriseById(Long id) {
        return entrepriseRepository.findById(id).orElse(null);
    }

    public Entreprise createEntreprise(Entreprise entreprise) {
        return entrepriseRepository.save(entreprise);
    }

    public Entreprise updateEntreprise(Long id, Entreprise entrepriseDetails) {
        Entreprise entreprise = entrepriseRepository.findById(id).orElse(null);
        if (entreprise != null) {
            entreprise.setNom(entrepriseDetails.getNom());
            entreprise.setAdresse(entrepriseDetails.getAdresse());
            entreprise.setEmail(entrepriseDetails.getEmail());
            entreprise.setTelephone(entrepriseDetails.getTelephone());
            entreprise.setSecteurActivite(entrepriseDetails.getSecteurActivite());
            return entrepriseRepository.save(entreprise);
        }
        return null;
    }

    public void deleteEntreprise(Long id) {
        entrepriseRepository.deleteById(id);
    }
}