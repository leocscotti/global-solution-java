package br.com.fiap.globalsolution.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.globalsolution.dtos.ResiduoDTO;
import br.com.fiap.globalsolution.models.Residuo;
import br.com.fiap.globalsolution.services.ResiduoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/residuos", produces = {"application/json"})
@Tag(name = "api-residuo")
public class ResiduoController {
     private final ResiduoService residuoService;

    @Autowired
    public ResiduoController(ResiduoService residuoService) {
        this.residuoService = residuoService;
    }

    @Operation(summary = "Retorna todos os residuos em páginas de 3")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum residuo encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<ResiduoDTO>> findAll() {
        Page<ResiduoDTO> residuosDTO = residuoService.findAll();
        if (residuosDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (ResiduoDTO residuoDTO : residuosDTO){
                Long id = residuoDTO.getId();
                residuoDTO.add(linkTo(methodOn(ResiduoController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(residuosDTO);
    }

    @Operation(summary = "Retorna um residuo específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum residuo encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResiduoDTO> findById(@PathVariable Long id) {
        ResiduoDTO residuoDTO = residuoService.findById(id);
        if (residuoDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            residuoDTO.add(linkTo(methodOn(ResiduoController.class)
                    .findAll()).withRel("Lista de Residuos"));
        }
        return ResponseEntity.ok(residuoDTO);
    }

    @Operation(summary = "Grava um residuo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Residuo gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Residuo> save(@Valid @RequestBody Residuo residuo) {
        Residuo residuoSalvo = residuoService.save(residuo);
        return new ResponseEntity<>(residuoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um residuo com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Residuo atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Residuo> update(@PathVariable Long id, @Valid @RequestBody Residuo residuo) {
        Residuo residuoSalvo = residuoService.update(id, residuo);
        return new ResponseEntity<>(residuoSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um residuo com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Residuo excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        residuoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
