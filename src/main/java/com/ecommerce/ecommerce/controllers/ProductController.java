package com.ecommerce.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.product.CreateProductDTO;
import com.ecommerce.ecommerce.product.ProductEntity;
import com.ecommerce.ecommerce.product.ProductRepository;
import com.ecommerce.ecommerce.product.ProductResponseDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public CreateProductDTO createProduct(@RequestBody @Valid CreateProductDTO data) {
        this.productRepository.save(new ProductEntity(data));

        return data;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getProducts() {
        List<ProductResponseDTO> data = this.productRepository
            .findAll()
            .stream()
            .map(ProductResponseDTO::new)
            .toList();

        return ResponseEntity.ok(data);
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<ProductEntity> getById(@PathVariable("id") String productId) {
        ProductEntity product = this.productRepository.findById(productId).get();
        return ResponseEntity.ok(product);
    }
}
