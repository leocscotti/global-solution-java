package br.com.fiap.globalsolution.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.fiap.globalsolution.dtos.AutoridadeAmbientalDTO;
import br.com.fiap.globalsolution.models.AutoridadeAmbiental;
import br.com.fiap.globalsolution.repositories.AutoridadeAmbientalRepository;

@Service
public class AutoridadeAmbientalService {
     private final AutoridadeAmbientalRepository autoridadeambientalRepository;
    private static final Pageable customPageable = PageRequest.of(0, 4, Sort.by("nome").ascending());
    @Autowired
    public AutoridadeAmbientalService(AutoridadeAmbientalRepository autoridadeambientalRepository) {
        this.autoridadeambientalRepository = autoridadeambientalRepository;
    }

    public Page<AutoridadeAmbientalDTO> findAll() {
        return autoridadeambientalRepository.findAll(customPageable).map(this::toDTO);
    }

    public AutoridadeAmbientalDTO findById(Long id) {
        Optional<AutoridadeAmbiental> autoridadeambiental = autoridadeambientalRepository.findById(id);
        return autoridadeambiental.map(this::toDTO).orElse(null);
    }

    public AutoridadeAmbiental save(AutoridadeAmbiental autoridadeambiental) {
        return autoridadeambientalRepository.save(autoridadeambiental);
    }

    public AutoridadeAmbiental update(Long id, AutoridadeAmbiental autoridadeambiental) {
        Optional<AutoridadeAmbiental> autoridadeambientalOptional = autoridadeambientalRepository.findById(id);
        if (autoridadeambientalOptional.isPresent()) {
            AutoridadeAmbiental autoridadeambientalUpdate = autoridadeambientalOptional.get();
            autoridadeambientalUpdate.setNome(autoridadeambiental.getNome());
            autoridadeambientalUpdate.setCargo(autoridadeambiental.getCargo());
            autoridadeambientalUpdate.setTipo(autoridadeambiental.getTipo());
            autoridadeambiental = autoridadeambientalRepository.save(autoridadeambientalUpdate);
            return autoridadeambiental;
        }
        return null;
    }

    public void delete(Long id) {
        Optional<AutoridadeAmbiental> autoridadeambientalOptional = autoridadeambientalRepository.findById(id);
        autoridadeambientalOptional.ifPresent(autoridadeambientalRepository::delete);
    }

    private AutoridadeAmbientalDTO toDTO(AutoridadeAmbiental autoridadeambiental) {
        AutoridadeAmbientalDTO autoridadeambientalDTO = new AutoridadeAmbientalDTO();
        autoridadeambientalDTO.setId(autoridadeambiental.getId());
        autoridadeambientalDTO.setNome(autoridadeambiental.getNome());
        autoridadeambientalDTO.setTipo(autoridadeambiental.getTipo());
        return autoridadeambientalDTO;
    }
}
