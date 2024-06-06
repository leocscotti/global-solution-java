package br.com.fiap.globalsolution.dtos;

import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class DenunciaDTO extends RepresentationModel <DenunciaDTO>{
    
    private Long id;
    private String descricao;
    private String situacao;
    private String localizacao;
}
