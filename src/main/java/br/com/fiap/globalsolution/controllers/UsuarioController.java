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

import br.com.fiap.globalsolution.dtos.UsuarioDTO;
import br.com.fiap.globalsolution.models.Usuario;
import br.com.fiap.globalsolution.services.UsuarioService;
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
@RequestMapping(value = "/usuarios", produces = {"application/json"})
@Tag(name = "api-usuario")
public class UsuarioController {
     private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @Operation(summary = "Retorna todos os usuarios em páginas de 3")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuario encontrado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping
    public ResponseEntity<Page<UsuarioDTO>> findAll() {
        Page<UsuarioDTO> usuariosDTO = usuarioService.findAll();
        if (usuariosDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            for (UsuarioDTO usuarioDTO : usuariosDTO){
                Long id = usuarioDTO.getId();
                usuarioDTO.add(linkTo(methodOn(UsuarioController.class)
                        .findById(id)).withSelfRel());
            }
        }
        return ResponseEntity.ok(usuariosDTO);
    }

    @Operation(summary = "Retorna um usuario específico por id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso"),
            @ApiResponse(responseCode = "204", description = "Nenhum usuario encontrado para o id informado", content = {
                    @Content(schema = @Schema())
            })
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id) {
        UsuarioDTO usuarioDTO = usuarioService.findById(id);
        if (usuarioDTO == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            usuarioDTO.add(linkTo(methodOn(UsuarioController.class)
                    .findAll()).withRel("Lista de Usuarios"));
        }
        return ResponseEntity.ok(usuarioDTO);
    }

    @Operation(summary = "Grava um usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario gravado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PostMapping
    public ResponseEntity<Usuario> save(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.save(usuario);
        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Atualiza um usuario com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuario atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de validação dos dados", content = {
                    @Content(schema = @Schema())
            })
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.update(id, usuario);
        return new ResponseEntity<>(usuarioSalvo, HttpStatus.CREATED);
    }

    @Operation(summary = "Exclui um usuario com base no id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuario excluído com sucesso", content = {
                    @Content(schema = @Schema())
            })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
