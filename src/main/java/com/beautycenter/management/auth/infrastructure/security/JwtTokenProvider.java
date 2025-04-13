package com.beautycenter.management.auth.infrastructure.security;

import com.beautycenter.management.auth.domain.model.AuthToken;
import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.domain.model.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Component for JWT token generation and validation.
 * This infrastructure component encapsulates the JWT token handling logic.
 */
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expiration.ms}")
    private int jwtExpirationMs;

    /**
     * Generate JWT token for a user.
     *
     * @param user the user
     * @return AuthToken containing the JWT
     */
    public AuthToken generateToken(User user) {
        log.debug("Generating JWT token for user: {}", user.getUsername());
        
        Instant now = Instant.now();
        Instant expiryDate = now.plus(jwtExpirationMs, ChronoUnit.MILLIS);
        
        // Generate JWT token
        String jwtToken = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(expiryDate))
                .claim("userId", user.getId())
                .claim("roles", user.getRoles().stream().map(Role::name).collect(Collectors.toList()))
                .signWith(getSigningKey(), SignatureAlgorithm.HS512)
                .setId(UUID.randomUUID().toString())
                .compact();
        
        // Create AuthToken
        return AuthToken.builder()
                .tokenValue(jwtToken)
                .tokenType("Bearer")
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .expiryDate(expiryDate)
                .build();
    }

    /**
     * Validate JWT token.
     *
     * @param token the JWT token
     * @return true if valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        } catch (Exception e) {
            log.error("JWT validation error: {}", e.getMessage());
        }
        return false;
    }

    /**
     * Extract username from JWT token.
     *
     * @param token the JWT token
     * @return username
     */
    public String getUsernameFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    /**
     * Extract user ID from JWT token.
     *
     * @param token the JWT token
     * @return user ID
     */
    public Long getUserIdFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("userId", Long.class);
    }

    /**
     * Extract roles from JWT token.
     *
     * @param token the JWT token
     * @return set of roles
     */
    @SuppressWarnings("unchecked")
    public Set<Role> getRolesFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .get("roles", Set.class);
    }

    /**
     * Get signing key for JWT.
     *
     * @return signing key
     */
    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}