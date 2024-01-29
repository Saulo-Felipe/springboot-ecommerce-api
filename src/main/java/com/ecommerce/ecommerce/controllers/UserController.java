package com.ecommerce.ecommerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.user.DTOs.CreateUserDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {

    @PostMapping
    public ResponseEntity<Object> signUp(@RequestBody @Valid CreateUserDTO body) {
        return ResponseEntity.status(200).body(body);
    }

    public void signIn() {

    }

}
