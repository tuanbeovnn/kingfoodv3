package com.kingfood.backend.securityconfig.oath2.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Component
public class TokenProvider {
    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 24 * 30;

    private final static String SECRET = "javanuise";

    UsernamePasswordAuthenticationToken getAuthentication(Collection<? extends GrantedAuthority> authorities, final UserDetails userDetails) {
        return new UsernamePasswordAuthenticationToken(userDetails, "", authorities);
    }

    public String generateToken(UserDetails userDetails) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> claims = objectMapper.convertValue(userDetails, Map.class);
        return doGenerateToken(claims, userDetails.getUsername());
    }
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();
    }
}
