package com.ship.projetoteste.cadastro.model;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "usuario")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;
}
