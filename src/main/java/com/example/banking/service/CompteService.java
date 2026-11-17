package com.example.banking.service;

import com.example.banking.dto.CompteRequestDto;
import com.example.banking.dto.CompteResponseDto;

import java.util.List;

public interface CompteService {
    List<CompteResponseDto> findAll();

    CompteResponseDto findById(String id);

    List<CompteResponseDto> findByClientId(Long clientId);

    CompteResponseDto create(CompteRequestDto dto);

    CompteResponseDto update(String id, CompteRequestDto dto);

    void delete(String id);
}
