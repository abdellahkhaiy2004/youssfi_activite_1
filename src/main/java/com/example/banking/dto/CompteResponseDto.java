package com.example.banking.dto;

import com.example.banking.entities.Typecompte;

import java.util.Date;

public record CompteResponseDto(String id, Double solde, Date dateCreation, Typecompte type, String devise) {
}
