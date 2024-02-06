package com.example.demo.domain.user;

public record UserRegisterRequestDTO(
    String name,
    String password,
    String email    
) {
    
}
