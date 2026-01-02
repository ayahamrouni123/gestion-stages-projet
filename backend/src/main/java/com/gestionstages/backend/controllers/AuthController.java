package com.gestionstages.backend.controllers;

import com.gestionstages.backend.dto.JwtResponse;
import com.gestionstages.backend.dto.LoginRequest;
import com.gestionstages.backend.models.Encadrant;
import com.gestionstages.backend.models.Etudiant;
import com.gestionstages.backend.repositories.EncadrantRepository;
import com.gestionstages.backend.repositories.EtudiantRepository;
import com.gestionstages.backend.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private EncadrantRepository encadrantRepository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Authentifier via Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateToken(authentication);

        // Récupérer l'ID réel de l'utilisateur
        Long userId = null;
        String role = "USER";

        Etudiant etudiant = etudiantRepository.findByEmail(loginRequest.getEmail()).orElse(null);
        if (etudiant != null) {
            userId = etudiant.getId();
            role = etudiant.getRole().replace("ROLE_", "");
        } else {
            Encadrant encadrant = encadrantRepository.findByEmail(loginRequest.getEmail()).orElse(null);
            if (encadrant != null) {
                userId = encadrant.getId();
                role = encadrant.getRole().replace("ROLE_", "");
            }
        }

        return ResponseEntity.ok(new JwtResponse(jwt, userId, loginRequest.getEmail(), role));
    }
}