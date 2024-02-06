package com.example.demo.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.domain.user.User;

@Service
public class TokenService {
    @Value("${api.security.token}")
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            String token = JWT
                .create()
                .withIssuer("ecommerce-api")
                .withSubject(user.getEmail())
                .withExpiresAt(this.getExpirationDate(2))
                .sign(algorithm);

            return token;

        } catch(JWTCreationException e) {
            throw new RuntimeException("Erro ao gerar token");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            String decripted = JWT
                .require(algorithm)
                .withIssuer("ecommerce-api")
                .build()
                .verify(token)
                .getSubject();

            return decripted;

        } catch(JWTVerificationException exception) {
            return null;
        }
    }

    public Instant getExpirationDate(Integer hours) {
        return LocalDateTime.now().plusHours(hours).toInstant(ZoneOffset.of("-03:00"));
    }
}
