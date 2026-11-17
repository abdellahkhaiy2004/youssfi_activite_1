package com.example.banking.mappers;

import com.example.banking.dto.CompteRequestDto;
import com.example.banking.dto.CompteResponseDto;
import com.example.banking.entities.Compte;
import org.springframework.stereotype.Component;

@Component
public class CompteMapper {
    public Compte toEntity(CompteRequestDto dto) {
        return Compte.builder()
                .solde(dto.solde())
                .devise(dto.devise())
                .type(dto.type())
                .build();
    }

    public CompteResponseDto toResponse(Compte compte) {
        return new CompteResponseDto(
                compte.getId(), compte.getSolde(), compte.getDateCreation(), compte.getType(), compte.getDevise()
        );
    }
}
