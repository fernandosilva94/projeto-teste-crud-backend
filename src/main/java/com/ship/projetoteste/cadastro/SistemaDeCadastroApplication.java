package com.ship.projetoteste.cadastro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = "controller")
public class SistemaDeCadastroApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaDeCadastroApplication.class, args);
	}

}
