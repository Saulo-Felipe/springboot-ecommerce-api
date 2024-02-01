package com.ecommerce.ecommerce.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.ecommerce.infra.TokenResponseDTO;
import com.ecommerce.ecommerce.infra.TokenService;
import com.ecommerce.ecommerce.user.UserEntity;
import com.ecommerce.ecommerce.user.DTOs.CreateUserDTO;
import com.ecommerce.ecommerce.user.DTOs.SignInUserDTO;

import jakarta.validation.Valid;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("auth/signup")
    public ResponseEntity<Object> signUp(@RequestBody @Valid CreateUserDTO body) {
        return ResponseEntity.status(200).body(body);
    }

    @PostMapping("auth/signin")
    public ResponseEntity<TokenResponseDTO> signIn(@RequestBody @Valid SignInUserDTO data) {
        System.out.println(data);
        UsernamePasswordAuthenticationToken tokenData = new UsernamePasswordAuthenticationToken(
            data.username(),
            data.password()
        );
        Authentication auth = this.manager.authenticate(tokenData);

        String token = this.tokenService.generateToken(data.username());

        return ResponseEntity
            .status(token == null ? 204 : 200)
            .body(new TokenResponseDTO(token));
    }
}
