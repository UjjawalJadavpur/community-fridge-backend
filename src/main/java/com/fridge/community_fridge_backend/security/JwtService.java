package com.fridge.community_fridge_backend.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fridge.community_fridge_backend.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtService {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    // Include userId as id in the token
    public String generateToken(User user) {
        return Jwts.builder()
                .claim("id", user.getId()) 
                .claim("name", user.getName())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignKey())
                .compact();
    }

    public String extractEmail(String token) {
        return extractAllClaims(token).get("email", String.class);
    }

    // Extract the user id from the token
    public Long extractUserId(String token) {
        return extractAllClaims(token).get("id", Long.class);  // Extract id
    }

    public boolean isTokenValid(String token, User user) {
        final String email = extractEmail(token);
        final Long userId = extractUserId(token);  // Extract userId from token
        return (email.equals(user.getEmail()) && userId.equals(user.getId())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    private Claims extractAllClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getSignKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
                | IllegalArgumentException e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    private Key getSignKey() {
        // jwtSecret must be at least 32 characters (256 bits) for HS256
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
}
