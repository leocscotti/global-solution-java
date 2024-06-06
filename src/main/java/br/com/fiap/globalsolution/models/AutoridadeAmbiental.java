package br.com.fiap.globalsolution.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_autoridade_ambiental")

public class AutoridadeAmbiental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min= 4, message = "O nome é obrigatório")
    @Column(name = "nome_autoridade_ambiental")
    private String nome;

    @NotBlank(message = "O tipo é obrigatório")
    @Column(name = "tipo")
    private String tipo;

    @NotBlank(message = "O cargo é obrigatório")
    @Column(name = "cargo")
    private String cargo;
}