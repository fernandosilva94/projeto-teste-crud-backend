package com.ship.projetoteste.cadastro.controller;

import com.ship.projetoteste.cadastro.config.JwtUtils;
import com.ship.projetoteste.cadastro.dto.AuthenticationRequestDTO;
import com.ship.projetoteste.cadastro.model.MyUserDetails;
import com.ship.projetoteste.cadastro.model.User;
import com.ship.projetoteste.cadastro.service.MyUserDetailsService;
import com.ship.projetoteste.cadastro.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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