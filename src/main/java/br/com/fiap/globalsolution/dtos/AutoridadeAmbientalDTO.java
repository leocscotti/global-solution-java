package br.com.fiap.globalsolution.dtos;

import org.springframework.hateoas.RepresentationModel;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AutoridadeAmbientalDTO extends RepresentationModel<AutoridadeAmbientalDTO> {
    
    private Long id;
    private String nome;
    private String tipo;

}
