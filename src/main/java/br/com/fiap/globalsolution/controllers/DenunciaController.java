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

import br.com.fiap.globalsolution.dtos.DenunciaDTO;
import br.com.fiap.globalsolution.models.Denuncia;
import br.com.fiap.globalsolution.services.DenunciaService;
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
@RequestMapping(value = "/denuncias", produces = {"application/json"})
@Tag(name = "api-denuncia")
public class DenunciaController {
    private final DenunciaService denunciaService;

    @Autowired
    public DenunciaController(DenunciaService denunciaService) {
        this.denunciaService = denunciaService;
    }

    @Operation(summary = "Retorna todas as denuncias em páginas de 5")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma denuncia encontrada", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<DenunciaDTO>> findAll() {
        Page<DenunciaDTO> denunciasDTO = denunciaService.findAll();
        if (denunciasDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (DenunciaDTO denunciaDTO : denunciasDTO){
                Long id = denunciaDTO.getId();
                denunciaDTO.add(linkTo(methodOn(DenunciaController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(denunciasDTO);
    }

    @Operation(summary = "Retorna uma denuncia específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma denuncia encontrada para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<DenunciaDTO> findById(@PathVariable Long id) {
        DenunciaDTO denunciaDTO = denunciaService.findById(id);
        if (denunciaDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            denunciaDTO.add(linkTo(methodOn(DenunciaController.class)
                    .findAll()).withRel("Lista de Denuncias"));
        }
        return ResponseEntity.ok(denunciaDTO);
    }

    @Operation(summary = "Grava uma denuncia")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Denuncia gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Denuncia> save(@Valid @RequestBody Denuncia denuncia) {
        Denuncia denunciaSalvo = denunciaService.save(denuncia);
        return new ResponseEntity<>(denunciaSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza uma denuncia com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Denuncia atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Denuncia> update(@PathVariable Long id, @Valid @RequestBody Denuncia denuncia) {
        Denuncia denunciaSalvo = denunciaService.update(id, denuncia);
        return new ResponseEntity<>(denunciaSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui uma denuncia com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Denuncia excluída com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        denunciaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
