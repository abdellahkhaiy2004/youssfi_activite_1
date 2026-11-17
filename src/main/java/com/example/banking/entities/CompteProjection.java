package com.example.banking.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "p1", types = Compte.class)
public interface CompteProjection {
    String getId();

    Double getSolde();

    Typecompte getType();
}
