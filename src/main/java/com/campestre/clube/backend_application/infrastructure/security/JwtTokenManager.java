package com.campestre.clube.backend_application.infrastructure.security;

import com.campestre.clube.backend_application.core.adapter.TokenGeneratorGateway;
import com.campestre.clube.backend_application.core.domain.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

import static com.campestre.clube.backend_application.core.exceptions.ExceptionExtensions.*;

@Component
public class JwtTokenManager implements TokenGeneratorGateway {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.validity}")
    private long jwtTokenValidity;

    @Override
    public String generate(Account account) {
        return Jwts.builder()
                .setSubject(account.getEmail().getValue())
                .claim("role", account.getAccess().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity))
                .signWith(parseSecret())
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        if (isTokenExpired(token)) throw UNAUTHORIZED_EXPIRED_TOKEN;
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getRoleFromToken(String token) {
        return getClaimFromToken(token, claims -> claims.get("role", String.class));
    }

    @Override
    public boolean validateToken(String token, String username) {
        String subject = getUsernameFromToken(token);
        return (subject.equals(username) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDateFromToken(token);
        return expirationDate.before(new Date(System.currentTimeMillis()));
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(parseSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey parseSecret() {
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
}
