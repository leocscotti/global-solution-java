package br.com.fiap.globalsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.globalsolution.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
}
