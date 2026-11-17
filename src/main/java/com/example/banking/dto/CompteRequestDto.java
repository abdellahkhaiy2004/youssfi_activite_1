package com.example.banking.dto;

import com.example.banking.entities.Typecompte;

public record CompteRequestDto(Double solde, String devise, Typecompte type) {
}
