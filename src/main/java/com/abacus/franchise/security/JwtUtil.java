package com.abacus.franchise.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String ACCESS_SECRET =
            "bXlfYWNjZXNzX3NlY3JldF9rZXlfMjU2X2JpdF9sb25n";
//    private static final String REFRESH_SECRET =
//            "bXlfcmVmcmVzaF9zZWNyZXRfa2V5XzI1Nl9iaXRfbG9uZw==";

//    private static final long ACCESS_EXPIRY = 1000 * 60 * 1; // 1 minute

//    private static final long ACCESS_EXPIRY = 1000 * 60 * 15;        // 15 min
//    private static final long REFRESH_EXPIRY = 1000 * 60 * 60 * 24 * 7; // 7 days

    private Key getAccessKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(ACCESS_SECRET));
    }

//    private Key getRefreshKey() {
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(REFRESH_SECRET));
//    }

    // ----------------- ACCESS TOKEN -----------------
    public String generateAccessToken(String username, String role) {
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRY))
                .signWith(SignatureAlgorithm.HS256,getAccessKey())
                .compact();
    }

    // ----------------- REFRESH TOKEN -----------------
//    public String generateRefreshToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRY))
//                .signWith(SignatureAlgorithm.HS256,getRefreshKey())
//                .compact();
//    }

    // ----------------- EXTRACT CLAIMS -----------------
    public Claims extractAccessClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getAccessKey())
                .parseClaimsJws(token)
                .getBody();
    }

//    public Claims extractRefreshClaims(String token) {
//        return Jwts.parser()
//                .setSigningKey(getRefreshKey())
//                .parseClaimsJws(token)
//                .getBody();
//    }

    // ----------------- HELPERS -----------------
    public String getUsernameFromAccessToken(String token) {
        return extractAccessClaims(token).getSubject();
    }

    public String getRoleFromAccessToken(String token) {
        return extractAccessClaims(token).get("role", String.class);
    }
}
