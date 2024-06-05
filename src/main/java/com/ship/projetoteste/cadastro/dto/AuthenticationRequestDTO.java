package com.ship.projetoteste.cadastro.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequestDTO {
    private String email;
    private String password;
}
