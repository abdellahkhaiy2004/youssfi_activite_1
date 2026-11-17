package com.example.banking.repositories;

import com.example.banking.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByComptes_Id(String compteId);
}
