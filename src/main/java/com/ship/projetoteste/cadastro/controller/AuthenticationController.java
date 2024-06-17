package com.ship.projetoteste.cadastro.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.ship.projetoteste.cadastro.config.JwtUtils;
import com.ship.projetoteste.cadastro.dto.AuthenticationRequestDTO;
import com.ship.projetoteste.cadastro.service.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin("http://localhost:4200")
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsService myUserDetailsService;
    private final JwtUtils jwtUtils;

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequestDTO requestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(requestDTO.getEmail(), requestDTO.getPassword())
            );
            UserDetails userDetails = myUserDetailsService.loadUserByUsername(requestDTO.getEmail());

            if (userDetails != null) {
                String jwtToken = jwtUtils.generateToken(userDetails);
                return ResponseEntity.ok(jwtToken);
            }

            return ResponseEntity.status(400).body("Invalid credentials");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(400).body("Invalid credentials");
        }
    }

}
