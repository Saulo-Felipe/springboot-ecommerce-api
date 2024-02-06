package com.example.demo.domain.product.DTOs;

public record UpdateProductRequestDTO (
    String name,
    Float price,
    Boolean is_active
) {
    
}
