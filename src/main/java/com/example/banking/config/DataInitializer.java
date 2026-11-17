
package com.example.banking.config;

import com.example.banking.entities.Client;
import com.example.banking.entities.Compte;
import com.example.banking.entities.Typecompte;
import com.example.banking.repositories.ClientRepository;
import com.example.banking.repositories.CompteRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;
import java.util.UUID;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(
            CompteRepository repository, ClientRepository clientRepository) {

        return args -> {
            Client alice = clientRepository.save(new Client("Alice"));
            Client samir = clientRepository.save(new Client("Samir"));

            // Compte 1 : COURANT
            Compte compte1 = Compte.builder()
                    .id(UUID.randomUUID().toString())
                    .solde(1500.0)
                    .dateCreation(new Date())
                    .type(Typecompte.COURANT)
                    .devise("MAD")
                    .client(alice)
                    .build();

            // Compte 2 : EPARGNE
            Compte compte2 = Compte.builder()
                    .id(UUID.randomUUID().toString())
                    .solde(7800.0)
                    .dateCreation(new Date())
                    .type(Typecompte.EPARGNE)
                    .devise("MAD")
                    .client(alice)
                    .build();

            // Compte 3 : COURANT
            Compte compte3 = Compte.builder()
                    .id(UUID.randomUUID().toString())
                    .solde(3250.0)
                    .dateCreation(new Date())
                    .type(Typecompte.COURANT)
                    .devise("EUR")
                    .client(samir)
                    .build();

            // Enregistrer les comptes dans H2
            repository.save(compte1);
            repository.save(compte2);
            repository.save(compte3);

            // Afficher tous les comptes
            System.out.println(
                    "===== COMPTES BANCAIRES ====="
            );

            repository.findAll().forEach(compte ->
                    System.out.println(
                            "ID : " + compte.getId()
                                    + " | Solde : " + compte.getSolde()
                                    + " | Type : " + compte.getType()
                    )
            );

            System.out.println("============================");
        };
    }
}
