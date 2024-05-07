package model;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
}
