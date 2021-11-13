package com.kingfood.backend.securityconfig.oath2.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingfood.backend.securityconfig.oath2.dto.UserPrincipalOauth2;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

@Service
public class TokenProvider {
    public static final long JWT_TOKEN_VALIDITY = 60 * 60 * 24 * 30;
    private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

    private final static String SECRET = "javanuise";

    private final AppProperties appProperties;

    public TokenProvider(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    public String createToken(Authentication authentication) {
        UserPrincipalOauth2 userPrincipal = (UserPrincipalOauth2) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationMsec());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getUserId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(appProperties.getAuth().getTokenSecret())
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();

        return (String) claims.get("email");
    }


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

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}
