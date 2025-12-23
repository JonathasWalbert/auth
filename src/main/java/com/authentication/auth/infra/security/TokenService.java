package com.authentication.auth.infra.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.authentication.auth.domain.user.User;

@Service
public class TokenService {
    private static final String ISSUER = "API Authentication";

    @Value("{api.security.token.secret}")
    private String secret;

    public String generateToken(User user){
        try{
            var algoritmo = Algorithm.HMAC256(secret);
        return JWT.create()
            .withIssuer(ISSUER)
            .withSubject(user.getEmail())
            .withExpiresAt(dateExp())
            .sign(algoritmo);

        }catch(JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token: ", exception);
        }
    }

    public String getSubject(String tokenJWT){
        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.require(algoritmo)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(tokenJWT)
                    .getSubject();

        } catch (JWTVerificationException exception){
            throw new RuntimeException("Token JWT invalido ou expirado" + tokenJWT);
        }
    }

    private Instant dateExp(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
