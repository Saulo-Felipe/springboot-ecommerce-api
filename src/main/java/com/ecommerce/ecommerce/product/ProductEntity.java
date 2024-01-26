package com.ecommerce.ecommerce.product;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
public class ProductEntity {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;
    private Float price;
    private String image_url;
    private Integer amount;

    @Enumerated(EnumType.STRING)
    private ProductType type;

    public ProductEntity(CreateProductDTO newProduct) {
        this.title = newProduct.title();
        this.amount = newProduct.amount();
        this.image_url = newProduct.image_url();
        this.price = newProduct.price();
        this.type = newProduct.type();
    }
}
