package com.ship.projetoteste.cadastro.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O email não pode estar em branco")
    private String email;

    @NotBlank(message = "O cpf não pode estar em branco")
    private String cpf;

    @NotBlank(message = "A senha não pode estar em branco")
    @JsonIgnore
    private String senha;
}
