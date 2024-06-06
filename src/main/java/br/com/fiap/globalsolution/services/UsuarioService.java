package br.com.fiap.globalsolution.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.dtos.UsuarioDTO;
import br.com.fiap.globalsolution.models.Usuario;
import br.com.fiap.globalsolution.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private static final Pageable customPageable = PageRequest.of(0, 3, Sort.by("nome").ascending());
    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Page<UsuarioDTO> findAll() {
        return usuarioRepository.findAll(customPageable).map(this::toDTO);
    }

    public UsuarioDTO findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.map(this::toDTO).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        if (usuarioOptional.isPresent()) {
            Usuario usuarioUpdate = usuarioOptional.get();
            usuarioUpdate.setNome(usuario.getNome());
            usuarioUpdate.setEmail(usuario.getEmail());
            usuarioUpdate.setSenha(usuario.getSenha());
            usuario = usuarioRepository.save(usuarioUpdate);
            return usuario;
        }
        return null;
    }

    public void delete(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        usuarioOptional.ifPresent(usuarioRepository::delete);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }
}
