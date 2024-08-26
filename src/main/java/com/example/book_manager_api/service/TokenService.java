package com.example.book_manager_api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    private static final String SECRET = "12345";


    public String gerarToken (String password) {
        if (password.equals(SECRET)) {
            try {
                Algorithm algorithm = Algorithm.HMAC256(SECRET);
                return JWT.create()
                        .withIssuer("API book-manager")
                        .withExpiresAt(dataExpiracao())
                        .sign(algorithm);
            } catch (JWTCreationException exception){
                throw new RuntimeException("Erro ao gerar token jwt", exception);
            }
        } else {
            return null;
        }
    }

    public boolean validarToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("API book-manager")
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
