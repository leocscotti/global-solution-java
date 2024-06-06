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

import br.com.fiap.globalsolution.dtos.AutoridadeAmbientalDTO;
import br.com.fiap.globalsolution.models.AutoridadeAmbiental;
import br.com.fiap.globalsolution.services.AutoridadeAmbientalService;
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
@RequestMapping(value = "/autoridadesambientais", produces = {"application/json"})
@Tag(name = "api-autoridadesambientais")

public class AutoridadeAmbientalController {
    private final AutoridadeAmbientalService autoridadeambientalService;

    @Autowired
    public AutoridadeAmbientalController(AutoridadeAmbientalService autoridadeambientalService) {
        this.autoridadeambientalService = autoridadeambientalService;
    }

    @Operation(summary = "Retorna todas as autoridades ambientais em páginas de 4")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma autoridade ambiental encontrada", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<AutoridadeAmbientalDTO>> findAll() {
        Page<AutoridadeAmbientalDTO> autoridadeambientalsDTO = autoridadeambientalService.findAll();
        if (autoridadeambientalsDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (AutoridadeAmbientalDTO autoridadeambientalDTO : autoridadeambientalsDTO){
                Long id = autoridadeambientalDTO.getId();
                autoridadeambientalDTO.add(linkTo(methodOn(AutoridadeAmbientalController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(autoridadeambientalsDTO);
    }

    @Operation(summary = "Retorna uma autoridade ambiental específica por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhuma autoridade ambiental encontrada para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<AutoridadeAmbientalDTO> findById(@PathVariable Long id) {
        AutoridadeAmbientalDTO autoridadeambientalDTO = autoridadeambientalService.findById(id);
        if (autoridadeambientalDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            autoridadeambientalDTO.add(linkTo(methodOn(AutoridadeAmbientalController.class)
                    .findAll()).withRel("Lista de Autoridades Ambientais"));
        }
        return ResponseEntity.ok(autoridadeambientalDTO);
    }

    @Operation(summary = "Grava uma autoridade ambiental")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autoridade Ambiental gravada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<AutoridadeAmbiental> save(@Valid @RequestBody AutoridadeAmbiental autoridadeambiental) {
        AutoridadeAmbiental autoridadeambientalSalvo = autoridadeambientalService.save(autoridadeambiental);
        return new ResponseEntity<>(autoridadeambientalSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza uma autoridade ambiental com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Autoridade Ambiental atualizada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<AutoridadeAmbiental> update(@PathVariable Long id, @Valid @RequestBody AutoridadeAmbiental autoridadeambiental) {
        AutoridadeAmbiental autoridadeambientalSalvo = autoridadeambientalService.update(id, autoridadeambiental);
        return new ResponseEntity<>(autoridadeambientalSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui uma autoridade ambiental com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Autoridade Ambiental excluída com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        autoridadeambientalService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
