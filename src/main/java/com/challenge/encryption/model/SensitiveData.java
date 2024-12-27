package com.challenge.encryption.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SensitiveData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String userDocument;

    @Column(nullable = false)
    private String creditCardToken;

    @Column(nullable = false)
    private long value;
}
