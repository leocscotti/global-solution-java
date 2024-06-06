package br.com.fiap.globalsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.globalsolution.models.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {
    
}