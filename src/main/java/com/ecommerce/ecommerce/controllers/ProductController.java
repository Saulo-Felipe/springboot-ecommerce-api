package com.ecommerce.ecommerce.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.ecommerce.product.ProductEntity;
import com.ecommerce.ecommerce.product.ProductRepository;
import com.ecommerce.ecommerce.product.DTOs.CreateProductDTO;
import com.ecommerce.ecommerce.product.DTOs.ProductResponseDTO;
import com.ecommerce.ecommerce.product.DTOs.UpdateProductDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @PostMapping
    @Transactional
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

    @PutMapping("/{id}")
    @Transactional
    public ProductEntity updateProduct(
        @PathVariable("id") String productId,
        @RequestBody @Valid UpdateProductDTO body
    ) {
        ProductEntity product = this.productRepository.findById(productId).get();

        product.update(body);

        return product;
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        this.productRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/by-id/{id}")
    public ResponseEntity<Optional<ProductEntity>> getById(@PathVariable("id") String productId) {
        Optional<ProductEntity> product = this.productRepository.findById(productId);

        if (product.isEmpty()) {
            throw new IllegalArgumentException();
        }

        return ResponseEntity.ok(product);
    }
}
