package com.example.banking.service;

import com.example.banking.dto.CompteRequestDto;
import com.example.banking.dto.CompteResponseDto;
import com.example.banking.entities.Compte;
import com.example.banking.mappers.CompteMapper;
import com.example.banking.repositories.CompteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CompteServiceImpl implements CompteService {
    private final CompteRepository repository;
    private final CompteMapper mapper;

    @Override
    public List<CompteResponseDto> findAll() {
        return repository.findAll().stream().map(mapper::toResponse).toList();
    }

    @Override
    public CompteResponseDto findById(String id) {
        return mapper.toResponse(chargerCompte(id));
    }

    @Override
    public List<CompteResponseDto> findByClientId(Long clientId) {
        return repository.findByClientId(clientId).stream().map(mapper::toResponse).toList();
    }

    @Override
    @Transactional
    public CompteResponseDto create(CompteRequestDto dto) {
        Compte compte = mapper.toEntity(dto);
        compte.initialiserCreation();
        return mapper.toResponse(repository.save(compte));
    }

    @Override
    @Transactional
    public CompteResponseDto update(String id, CompteRequestDto dto) {
        Compte compte = chargerCompte(id);
        if (dto.solde() != null) {
            compte.setSolde(dto.solde());
        }
        if (dto.devise() != null) {
            compte.setDevise(dto.devise());
        }
        if (dto.type() != null) {
            compte.setType(dto.type());
        }
        return mapper.toResponse(compte);
    }

    @Override
    @Transactional
    public void delete(String id) {
        repository.delete(chargerCompte(id));
    }

    private Compte chargerCompte(String id) {
        return repository.findById(id).orElseThrow(() -> new CompteIntrouvableException(id));
    }
}
