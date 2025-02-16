package com.mycompany.e_commerce.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;

@Component
public class JWTManager {
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.duration}")
    private Long duration;

    public String generateToken(Long id) {
        try {
            return JWT.create()
                .withClaim("id", id)
                .withIssuer("mycompany.com")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + duration))
                .sign(Algorithm.HMAC512(secretKey));
        } catch (Exception e) {
            e.getMessage();
            return null;
        }
    }

    public Optional<Long> validToken(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC512(secretKey)).withIssuer("mycompany.com")
                    .build();
            DecodedJWT decodedJWT = jwtVerifier.verify(token);

            return Optional.of(decodedJWT.getClaim("id").asLong());
        } catch (JWTVerificationException e) {
            e.getMessage();
        }
        return Optional.empty();
    }
}
