
package com.example.banking.repositories;

import com.example.banking.entities.Compte;
import com.example.banking.entities.Typecompte;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

@RepositoryRestResource(path = "comptes", collectionResourceRel = "comptes")
public interface CompteRepository
        extends JpaRepository<Compte, String> {

    // Rechercher les comptes par type
    @RestResource(path = "byType", rel = "byType")
    List<Compte> findByType(@Param("t") Typecompte type);

    // Rechercher les comptes dont le solde
    // est superieur a une valeur
    @RestResource(exported = false)
    List<Compte> findBySoldeGreaterThan(Double montant);

    // Rechercher les comptes par type et solde
    @RestResource(exported = false)
    List<Compte> findByTypeAndSoldeGreaterThan(
            Typecompte type,
            Double montant
    );

    @RestResource(exported = false)
    List<Compte> findByClientId(Long clientId);
}
