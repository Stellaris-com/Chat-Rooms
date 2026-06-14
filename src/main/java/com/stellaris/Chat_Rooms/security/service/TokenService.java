package com.stellaris.Chat_Rooms.security.service;

import com.stellaris.Chat_Rooms.domain.enums.Role;
import com.stellaris.Chat_Rooms.persistence.entities.UserEntity;
import com.stellaris.Chat_Rooms.persistence.repositories.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class TokenService {
    private  Long EXPIRATION_TIME = 86400000L;

    private final TokenRepository tokenRepository;
    private final PrivateKey privateKey;
    private final PublicKey publicKey;

    public String generateAccessToken(UserEntity saved) {
        return Jwts.builder()
                .subject(saved.getUsername())
                .claim("userId", saved.getId())
                .claim("role", saved.getRole())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(privateKey)
                .compact();
    }

    public UUID extractUserId(String token) {
        return UUID.fromString(this.extractClaims(token, claims -> claims.get("userId", String.class)));
    }

    public Role extractRole(String token) {
        return Role.valueOf(this.extractClaims(token, claims -> claims.get("role", String.class)));
    }

    public boolean isValidToken(String token) {
        Date expiration = extractClaims(token, Claims::getExpiration);
        return (expiration.after(new Date(System.currentTimeMillis())) && this.findUserByToken(token));
    }

    private boolean findUserByToken(String token) {
        UUID userId = this.extractUserId(token);
        return tokenRepository.findByUserId(userId).isPresent();
    }

    public String extractUsername(String token) {
        return this.extractClaims(token, Claims::getSubject);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(publicKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return  claimsTFunction.apply(extractAllClaims(token));
    }
}
