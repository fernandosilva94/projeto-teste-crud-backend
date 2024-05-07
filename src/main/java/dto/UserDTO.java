package dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    @NotBlank(message = "O nome não pode estar em branco")
    private String nome;

    @NotBlank(message = "O nome não pode estar em branco")
    private String email;

    @NotBlank(message = "O nome não pode estar em branco")
    private String cpf;

    @NotBlank(message = "O nome não pode estar em branco")
    private String senha;
}
