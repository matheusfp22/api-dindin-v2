package com.example.dindin.dto;

public record ResponseDTO(
        String message,
        Object data,
        boolean success
) {
}
