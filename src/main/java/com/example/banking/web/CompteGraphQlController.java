package com.example.banking.web;

import com.example.banking.dto.CompteRequestDto;
import com.example.banking.dto.CompteResponseDto;
import com.example.banking.entities.Client;
import com.example.banking.repositories.ClientRepository;
import com.example.banking.service.CompteService;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CompteGraphQlController {
    private final CompteService compteService;
    private final ClientRepository clientRepository;

    @QueryMapping
    public List<CompteResponseDto> accountsList() {
        return compteService.findAll();
    }

    @QueryMapping
    public CompteResponseDto bankAccountById(@Argument String id) {
        return compteService.findById(id);
    }

    @QueryMapping
    public List<Client> customers() {
        return clientRepository.findAll();
    }

    @MutationMapping
    public CompteResponseDto addAccount(@Argument CompteRequestDto compte) {
        return compteService.create(compte);
    }

    @MutationMapping
    public CompteResponseDto updateAccount(@Argument String id, @Argument CompteRequestDto compte) {
        return compteService.update(id, compte);
    }

    @MutationMapping
    public boolean deleteAccount(@Argument String id) {
        compteService.delete(id);
        return true;
    }

    @SchemaMapping(typeName = "Compte")
    public String dateCreation(CompteResponseDto compte) {
        Date date = compte.dateCreation();
        return date == null ? null : Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .toString();
    }

    @SchemaMapping(typeName = "Compte")
    public Client client(CompteResponseDto compte) {
        return clientRepository.findByComptes_Id(compte.id()).orElse(null);
    }

    @SchemaMapping(typeName = "Client")
    public List<CompteResponseDto> comptes(Client client) {
        return compteService.findByClientId(client.getId());
    }
}
