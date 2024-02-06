package com.example.demo.controllers;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.user.UserLoginRequestDTO;
import com.example.demo.domain.user.UserRegisterRequestDTO;
import com.example.demo.domain.user.User;
import com.example.demo.domain.user.UserRepository;
import com.example.demo.infra.security.TokenService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @GetMapping()
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody @Valid UserLoginRequestDTO data) {
        try {
            UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
            Authentication auth = this.authenticationManager.authenticate(usernamePassword);

            String token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.status(200).body(Collections.singletonMap("token", token));

        } catch(AuthenticationException e) {
            return ResponseEntity.status(401).body(Collections.singletonMap("message", "Senha ou email inválido."));
        }
    } 

    @PostMapping("register")
    @Transactional
    public ResponseEntity<Object> register(@RequestBody @Valid UserRegisterRequestDTO data) {
        if (this.userRepository.findByEmail(data.email()) != null) {
            return ResponseEntity.status(409).body(Collections.singletonMap("message", "Esse email já está em uso"));
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        User user = new User(data.name(), encryptedPassword, data.email());

        this.userRepository.save(user);

        return ResponseEntity.status(201)
            .body(Collections.singletonMap("message", "Usuário criado com sucesso!"));

    }
}
