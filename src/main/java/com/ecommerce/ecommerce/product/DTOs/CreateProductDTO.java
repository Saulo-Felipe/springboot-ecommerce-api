package com.ecommerce.ecommerce.product.DTOs;

import com.ecommerce.ecommerce.product.ProductType;

import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateProductDTO(

    @NotBlank 
    @Size(min = 5, message = "O título precisa ter pelo menos 5 caracteres")
    String title, 

    @Positive
    Float price, 

    @NotBlank 
    @Size(min = 5, message = "A url da imagem precisa ter pelo menos 5 caracteres")
    String image_url, 

    @Positive
    Integer amount, 

    @Enumerated
    ProductType type
) {}