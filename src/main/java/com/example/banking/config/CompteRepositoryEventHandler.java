package com.example.banking.config;

import com.example.banking.entities.Compte;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class CompteRepositoryEventHandler {
    @HandleBeforeCreate
    public void initialiserCompte(Compte compte) {
        compte.initialiserCreation();
    }
}
