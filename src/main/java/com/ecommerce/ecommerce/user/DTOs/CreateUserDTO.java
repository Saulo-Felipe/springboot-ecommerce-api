package com.ecommerce.ecommerce.user.DTOs;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
    @NotBlank(message = "Username inválido")
    String username,

    @NotBlank(message = "Senha inválida")
    String password,

    @Email(message = "Email inválido")
    String email
) {}
