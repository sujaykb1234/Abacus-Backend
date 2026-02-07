package com.abacus.franchise.security;

import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    @Value("${jwt.access.secret}")
    private String accessSecret;

    @Value("${jwt.access.expiry:900000}")
    private long accessExpiry;

    private Key getAccessKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(accessSecret));
    }

    // ----------------- ACCESS TOKEN -----------------
    public String generateAccessToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + accessExpiry))
                .signWith(getAccessKey(), SignatureAlgorithm.ES256)
                .compact();
    }

    // ----------------- EXTRACT CLAIMS -----------------
    public Claims extractAccessClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getAccessKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ----------------- HELPERS -----------------
    public String getUsernameFromAccessToken(String token) {
        return extractAccessClaims(token).getSubject();
    }

    public String getRoleFromAccessToken(String token) {
        return extractAccessClaims(token).get("role", String.class);
    }
}
