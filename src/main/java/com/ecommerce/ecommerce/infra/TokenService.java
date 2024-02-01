package com.ecommerce.ecommerce.infra;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String secret;

    public String generateToken(String username) {
        try {
            System.out.println(this.secret);
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT
                .create()
                .withIssuer("ecommerce")
                .withSubject(username)
                .withExpiresAt(this.getExpiresDate())
                .sign(algorithm);

            return token;

        } catch (JWTCreationException exception){
            return null;
        }
    }

    public Instant getExpiresDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
