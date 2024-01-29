package com.ecommerce.ecommerce.product.DTOs;

import com.ecommerce.ecommerce.product.ProductType;

public record UpdateProductDTO(
    String title,
    Float price,
    String image_url,
    Integer amount,
    ProductType type
) {}
