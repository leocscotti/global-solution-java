package br.com.fiap.globalsolution.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_usuario")

public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome_usuario")
    private String nome;

    @Email(message = "Email inválido")
    @Column(name = "email")
    private String email;

    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
    @Column(name = "senha")
    private String senha;

}
