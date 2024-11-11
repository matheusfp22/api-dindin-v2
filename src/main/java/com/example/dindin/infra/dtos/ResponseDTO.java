package com.example.dindin.infra.dtos;

public record ResponseDTO(
        String message,
        Object data,
        boolean success
) {
}
