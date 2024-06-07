package br.com.fiap.globalsolution.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_residuo")

public class Residuo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(name = "descricao")
    private String descricao;

    @NotBlank(message = "O tipo é obrigatório")
    @Column(name = "tipo")
    private String tipo;

    @Min (value= 2, message = "a quantidade deve ter no minimo 2 digitos")
    @Column(name = "quantidade")
    private int quantidade;

}
