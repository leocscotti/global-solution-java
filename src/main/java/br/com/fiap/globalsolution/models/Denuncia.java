package br.com.fiap.globalsolution.models;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tb_denuncia")

public class Denuncia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A descrição é obrigatória")
    @Column(name = "descricao")
    private String descricao;

    @NotBlank(message = "a data é obrigatória")
    @Column(name = "dt_ocorrencia")
    private LocalDateTime dataOcorrencia;

    @NotBlank(message = "A situação é obrigatória")
    @Column(name = "situacao")
    private String situacao;

    @NotBlank(message = "A localizacao é obrigatória")
    @Column(name = "localizacao")
    private String localizacao;
}
