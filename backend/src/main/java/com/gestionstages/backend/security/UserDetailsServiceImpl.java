package com.gestionstages.backend.security;

import com.gestionstages.backend.models.Etudiant;
import com.gestionstages.backend.models.Encadrant;
import com.gestionstages.backend.repositories.EtudiantRepository;
import com.gestionstages.backend.repositories.EncadrantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EncadrantRepository encadrantRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Chercher d'abord dans les étudiants
        Etudiant etudiant = etudiantRepository.findByEmail(email).orElse(null);
        if (etudiant != null) {
            return User.withUsername(etudiant.getEmail())
                    .password(etudiant.getPassword())
                    .roles(etudiant.getRole().replace("ROLE_", ""))  // Enlève "ROLE_" pour hasRole()
                    .build();
        }

        // Chercher dans les encadrants
        Encadrant encadrant = encadrantRepository.findByEmail(email).orElse(null);
        if (encadrant != null) {
            return User.withUsername(encadrant.getEmail())
                    .password(encadrant.getPassword())
                    .roles(encadrant.getRole().replace("ROLE_", ""))  // Enlève "ROLE_" pour hasRole()
                    .build();
        }

        throw new UsernameNotFoundException("Utilisateur non trouvé avec l'email: " + email);
    }
}