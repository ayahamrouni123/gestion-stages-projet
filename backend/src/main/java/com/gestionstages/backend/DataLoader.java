package com.gestionstages.backend;

import com.gestionstages.backend.models.*;
import com.gestionstages.backend.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EncadrantRepository encadrantRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private StageRepository stageRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("=== CHARGEMENT DES DONNÉES DE TEST ===");

        // Vérifiez si des données existent déjà
        if (etudiantRepository.count() == 0) {
            // Créer un encadrant ADMIN
            Encadrant admin = new Encadrant();
            admin.setNom("Admin");
            admin.setPrenom("System");
            admin.setEmail("admin@test.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setDepartement("Administration");
            admin.setTelephone("0123456789");
            admin.setRole("ROLE_ADMIN");
            encadrantRepository.save(admin);

            // Créer un encadrant
            Encadrant encadrant = new Encadrant();
            encadrant.setNom("Professeur");
            encadrant.setPrenom("Dupont");
            encadrant.setEmail("encadrant@test.com");
            encadrant.setPassword(passwordEncoder.encode("password123"));
            encadrant.setDepartement("Informatique");
            encadrant.setTelephone("0987654321");
            encadrant.setRole("ROLE_ENCADRANT");
            encadrant = encadrantRepository.save(encadrant);

            // Créer un étudiant
            Etudiant etudiant = new Etudiant();
            etudiant.setNom("Etudiant");
            etudiant.setPrenom("Test");
            etudiant.setEmail("etudiant@test.com");
            etudiant.setPassword(passwordEncoder.encode("password123"));
            etudiant.setMatricule("E2024001");
            etudiant.setFiliere("Informatique");
            etudiant.setTelephone("0987654321");
            etudiant.setRole("ROLE_ETUDIANT");
            etudiant.setEncadrant(encadrant);
            etudiant = etudiantRepository.save(etudiant);

            // Créer une entreprise
            Entreprise entreprise = new Entreprise();
            entreprise.setNom("TechCorp");
            entreprise.setAdresse("123 Rue de la Tech, Paris");
            entreprise.setEmail("contact@techcorp.com");
            entreprise.setTelephone("0567891234");
            entreprise.setSecteurActivite("Technologie");
            entreprise = entrepriseRepository.save(entreprise);

            // Créer un stage
            Stage stage = new Stage();
            stage.setTitre("Stage en développement web");
            stage.setDescription("Développement d'une application avec Spring Boot et Angular");
            stage.setDateDebut(LocalDate.of(2024, 1, 15));
            stage.setDateFin(LocalDate.of(2024, 6, 15));
            stage.setStatut("EN_ATTENTE");
            stage.setRapportUrl(null);
            stage.setEtudiant(etudiant);
            stage.setEncadrant(encadrant);
            stage.setEntreprise(entreprise);
            stageRepository.save(stage);

            System.out.println("✅ Données de test créées avec succès !");
        }
    }
}