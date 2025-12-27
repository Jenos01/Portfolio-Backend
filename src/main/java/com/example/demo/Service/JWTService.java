package com.example.demo.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.function.Function;

@Service
public class JWTService {

    @Value("${jwt.secret}")
    private String secretKey;

    //private String secretKey = "";

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public JWTService() throws NoSuchAlgorithmException {
        //improvments(trying) ///why ? 5tr aala 9oddem fi kol marra tsir restart lel app mathaln bech yetgenera secret key jdid donc l existing tokens bch ywalliw invalid ( i think)
//        KeyGenerator keyGen= KeyGenerator.getInstance("HmacSHA256");
//        SecretKey sk = keyGen.generateKey();
//        secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    }

    //public String generateToken(String username) {
    public String generateToken(UserDetails userDetails) { //*

        Map<String, Object> claims = new HashMap<>();
        //*
        claims.put("role", userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList());

        return Jwts.builder()
                .claims()
                .add(claims)
               // .subject(username)
                .subject(userDetails.getUsername()) //*
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration)) //*
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token) {
        //extract the username from jwt token
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUserName(token);
        return(username.equals((userDetails.getUsername()))&& !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token){
        return extractClaims(token, Claims::getExpiration);
    }

    //*
    public List<String> extractRoles(String token) {
        return extractClaims(token, claims ->
                claims.get("role", List.class)
        );
    }

}
