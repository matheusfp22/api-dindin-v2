package com.example.dindin.infra.dtos.responses;

public record ResponseDTO(
        String message,
        Object data,
        boolean success
) {
}
