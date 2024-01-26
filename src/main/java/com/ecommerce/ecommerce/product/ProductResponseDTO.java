package com.ecommerce.ecommerce.product;

public record ProductResponseDTO(String id, String title, Float price, String image_url, Integer amount, ProductType type) {
    public ProductResponseDTO(ProductEntity product) {
        this(
            product.getId(),
            product.getTitle(), 
            product.getPrice(), 
            product.getImage_url(), 
            product.getAmount(), 
            product.getType()
        );
    }
}
