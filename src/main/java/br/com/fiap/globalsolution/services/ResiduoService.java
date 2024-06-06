package br.com.fiap.globalsolution.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.dtos.ResiduoDTO;
import br.com.fiap.globalsolution.models.Residuo;
import br.com.fiap.globalsolution.repositories.ResiduoRepository;

@Service
public class ResiduoService {
    private final ResiduoRepository residuoRepository;
    private static final Pageable customPageable = PageRequest.of(0, 3, Sort.by("tipo").ascending());
    @Autowired
    public ResiduoService(ResiduoRepository residuoRepository) {
        this.residuoRepository = residuoRepository;
    }

    public Page<ResiduoDTO> findAll() {
        return residuoRepository.findAll(customPageable).map(this::toDTO);
    }

    public ResiduoDTO findById(Long id) {
        Optional<Residuo> residuo = residuoRepository.findById(id);
        return residuo.map(this::toDTO).orElse(null);
    }

    public Residuo save(Residuo residuo) {
        return residuoRepository.save(residuo);
    }

    public Residuo update(Long id, Residuo residuo) {
        Optional<Residuo> residuoOptional = residuoRepository.findById(id);
        if (residuoOptional.isPresent()) {
            Residuo residuoUpdate = residuoOptional.get();
            residuoUpdate.setDescricao(residuo.getDescricao());
            residuoUpdate.setTipo(residuo.getTipo());
            residuoUpdate.setQuantidade(residuo.getQuantidade());
            residuo = residuoRepository.save(residuoUpdate);
            return residuo;
        }
        return null;
    }

    public void delete(Long id) {
        Optional<Residuo> residuoOptional = residuoRepository.findById(id);
        residuoOptional.ifPresent(residuoRepository::delete);
    }

    private ResiduoDTO toDTO(Residuo residuo) {
        ResiduoDTO residuoDTO = new ResiduoDTO();
        residuoDTO.setId(residuo.getId());
        residuoDTO.setTipo(residuo.getTipo());
        residuoDTO.setQuantidade(residuo.getQuantidade());
        return residuoDTO;
    }
}
