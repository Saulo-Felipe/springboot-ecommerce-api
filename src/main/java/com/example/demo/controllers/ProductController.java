package com.example.demo.controllers;


import java.util.Collections;
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

import com.example.demo.domain.product.Product;
import com.example.demo.domain.product.ProductRepository;
import com.example.demo.domain.product.DTOs.CreateProductRequestDTO;
import com.example.demo.domain.product.DTOs.UpdateProductRequestDTO;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(this.productRepository.findAll());
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Product> createProduct(@RequestBody @Valid CreateProductRequestDTO data) {
        Product product = new Product(data);

        return ResponseEntity.ok(this.productRepository.save(product));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> updateProduct(
        @RequestBody @Valid UpdateProductRequestDTO data,
        @PathVariable Integer id
    ) {
        try {
            Product product = this.productRepository.findById(id).get();

            product.setName(data.name());
            product.setPrice(data.price());

            return ResponseEntity.status(201).body(product);

        } catch(Exception e) {
            return ResponseEntity
                .status(400)
                .body(Collections.singletonMap("message", "Id inválido."));
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        try {
            Product product = this.productRepository.findById(id).get();

            product.setIs_active(false);
        } catch(Exception e) {}

        return ResponseEntity.status(204).build();
    }

}
