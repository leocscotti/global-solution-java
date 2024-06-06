package br.com.fiap.globalsolution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.globalsolution.models.Residuo;

public interface ResiduoRepository extends JpaRepository<Residuo, Long> {
    
}
