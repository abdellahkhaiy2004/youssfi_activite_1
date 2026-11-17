package com.example.banking.service;

public class CompteIntrouvableException extends RuntimeException {
    public CompteIntrouvableException(String id) {
        super("Le compte %s est introuvable.".formatted(id));
    }
}
