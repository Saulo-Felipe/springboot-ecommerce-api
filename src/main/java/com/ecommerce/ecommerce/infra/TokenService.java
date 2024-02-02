package com.ecommerce.ecommerce.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.ecommerce.ecommerce.user.UserEntity;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(UserEntity user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            String token = JWT
                .create()
                .withIssuer("ecommerce")
                .withSubject(user.getUsername())
                .withExpiresAt(this.getExpiresDate())
                .sign(algorithm);

            return token;

        } catch (JWTCreationException exception){
            return null;
        }
    }

    public String verifyTokenAndGetSubject(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.secret);

            return JWT.require(algorithm)
                .withIssuer("ecommerce")
                .build()
                .verify(token)
                .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token inválido");
        }
    }

    public Instant getExpiresDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
