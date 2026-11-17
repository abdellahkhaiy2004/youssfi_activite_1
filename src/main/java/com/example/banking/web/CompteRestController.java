
package com.example.banking.web;

import com.example.banking.dto.CompteRequestDto;
import com.example.banking.dto.CompteResponseDto;
import com.example.banking.service.CompteIntrouvableException;
import com.example.banking.service.CompteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comptes")
@RequiredArgsConstructor
@Tag(
        name = "Gestion des comptes bancaires",
        description = "Operations CRUD sur les comptes bancaires"
)
public class CompteRestController {

    private final CompteService compteService;

    // ==============================
    // GET : Tous les comptes
    // ==============================

    @Operation(
            summary = "Lister tous les comptes",
            description = "Retourne la liste des comptes bancaires"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Liste des comptes recuperee"
    )
    @GetMapping
    public List<CompteResponseDto> getAllComptes() {
        return compteService.findAll();
    }

    // ==============================
    // GET : Compte par ID
    // ==============================

    @Operation(
            summary = "Rechercher un compte",
            description = "Recherche un compte bancaire par ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Compte trouve"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Compte introuvable"
            )
    })
    @GetMapping("/{id}")
    public CompteResponseDto getCompteById(
            @Parameter(description = "Identifiant du compte")
            @PathVariable String id
    ) {
        return compteService.findById(id);
    }

    // ==============================
    // POST : Creation
    // ==============================

    @Operation(
            summary = "Creer un compte",
            description = "Cree un nouveau compte bancaire"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Compte cree avec succes"
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompteResponseDto createCompte(@RequestBody CompteRequestDto compte) {
        return compteService.create(compte);
    }

    // ==============================
    // PUT : Modification
    // ==============================

    @Operation(
            summary = "Modifier un compte",
            description = "Modifie les champs fournis (solde, devise, type)"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Compte modifie"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Compte introuvable"
            )
    })
    @PutMapping("/{id}")
    public CompteResponseDto updateCompte(
            @Parameter(description = "ID du compte a modifier")
            @PathVariable String id,
            @RequestBody CompteRequestDto updatedCompte
    ) {
        return compteService.update(id, updatedCompte);
    }

    // ==============================
    // DELETE : Suppression
    // ==============================

    @Operation(
            summary = "Supprimer un compte",
            description = "Supprime un compte bancaire par ID"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Compte supprime"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Compte introuvable"
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompte(
            @Parameter(description = "ID du compte a supprimer")
            @PathVariable String id
    ) {
        compteService.delete(id);
    }

    @ExceptionHandler(CompteIntrouvableException.class)
    public ResponseEntity<Void> compteIntrouvable() {
        return ResponseEntity.notFound().build();
    }
}
