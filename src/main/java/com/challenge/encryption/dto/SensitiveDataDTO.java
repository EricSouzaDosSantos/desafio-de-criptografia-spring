package com.challenge.encryption.dto;

public record SensitiveDataDTO(String userDocument, String creditCardToken, long value) {
}
