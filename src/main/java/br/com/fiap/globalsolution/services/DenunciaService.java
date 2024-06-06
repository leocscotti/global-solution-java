package br.com.fiap.globalsolution.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.dtos.DenunciaDTO;
import br.com.fiap.globalsolution.models.Denuncia;
import br.com.fiap.globalsolution.repositories.DenunciaRepository;

@Service
public class DenunciaService {
    private final DenunciaRepository denunciaRepository;
    private static final Pageable customPageable = PageRequest.of(0, 5, Sort.by("descricao").ascending());
    @Autowired
    public DenunciaService(DenunciaRepository denunciaRepository) {
        this.denunciaRepository = denunciaRepository;
    }

    public Page<DenunciaDTO> findAll() {
        return denunciaRepository.findAll(customPageable).map(this::toDTO);
    }

    public DenunciaDTO findById(Long id) {
        Optional<Denuncia> denuncia = denunciaRepository.findById(id);
        return denuncia.map(this::toDTO).orElse(null);
    }

    public Denuncia save(Denuncia denuncia) {
        return denunciaRepository.save(denuncia);
    }

    public Denuncia update(Long id, Denuncia denuncia) {
        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(id);
        if (denunciaOptional.isPresent()) {
            Denuncia denunciaUpdate = denunciaOptional.get();
            denunciaUpdate.setDescricao(denuncia.getDescricao());
            denunciaUpdate.setDataOcorrencia(denuncia.getDataOcorrencia());
            denunciaUpdate.setSituacao(denuncia.getSituacao());
            denunciaUpdate.setLocalizacao(denuncia.getLocalizacao());
            denuncia = denunciaRepository.save(denunciaUpdate);
            return denuncia;
        }
        return null;
    }

    public void delete(Long id) {
        Optional<Denuncia> denunciaOptional = denunciaRepository.findById(id);
        denunciaOptional.ifPresent(denunciaRepository::delete);
    }

    private DenunciaDTO toDTO(Denuncia denuncia) {
        DenunciaDTO denunciaDTO = new DenunciaDTO();
        denunciaDTO.setId(denuncia.getId());
        denunciaDTO.setDescricao(denuncia.getDescricao());
        denunciaDTO.setSituacao(denuncia.getSituacao());
        denunciaDTO.setLocalizacao(denuncia.getLocalizacao());
        return denunciaDTO;
    }
}
