
package com.example.banking.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "comptes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Compte {

    @Id
    private String id;

    private Double solde;

    private String devise;

    @Temporal(TemporalType.DATE)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    private Typecompte type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @RestResource(exported = false)
    private Client client;

    public void initialiserCreation() {
        id = UUID.randomUUID().toString();
        dateCreation = new Date();
    }
}
