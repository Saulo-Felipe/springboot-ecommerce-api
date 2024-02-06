package com.example.demo.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequestDTO(
    @NotBlank
    String email,
    @NotBlank
    String password
) {}
