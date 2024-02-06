package com.example.demo.domain.product;

import com.example.demo.domain.product.DTOs.CreateProductRequestDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "products")
@Entity(name = "products")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Float price;
    private Boolean is_active;

    public Product(CreateProductRequestDTO data) { // product creation without "id" field
        this.name = data.name();
        this.price = data.price();
        this.is_active = true;
    }
}
