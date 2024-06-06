package br.com.fiap.globalsolution.dtos;

import org.springframework.hateoas.RepresentationModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResiduoDTO extends RepresentationModel <ResiduoDTO>{
    private Long id;
    private String tipo;
    private int quantidade;
}
