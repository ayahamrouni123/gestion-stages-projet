package com.gestionstages.backend.services;

import com.gestionstages.backend.models.Etudiant;
import com.gestionstages.backend.repositories.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public Etudiant getEtudiantById(Long id) {
        return etudiantRepository.findById(id).orElse(null);
    }

    public Etudiant createEtudiant(Etudiant etudiant) {
        // Encoder le mot de passe
        etudiant.setPassword(passwordEncoder.encode(etudiant.getPassword()));
        return etudiantRepository.save(etudiant);
    }

    public Etudiant updateEtudiant(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = etudiantRepository.findById(id).orElse(null);
        if (etudiant != null) {
            etudiant.setNom(etudiantDetails.getNom());
            etudiant.setPrenom(etudiantDetails.getPrenom());
            etudiant.setEmail(etudiantDetails.getEmail());
            if (etudiantDetails.getPassword() != null && !etudiantDetails.getPassword().isEmpty()) {
                etudiant.setPassword(passwordEncoder.encode(etudiantDetails.getPassword()));
            }
            etudiant.setMatricule(etudiantDetails.getMatricule());
            etudiant.setFiliere(etudiantDetails.getFiliere());
            etudiant.setTelephone(etudiantDetails.getTelephone());
            return etudiantRepository.save(etudiant);
        }
        return null;
    }

    public void deleteEtudiant(Long id) {
        etudiantRepository.deleteById(id);
    }
}