package com.maharishi.may.ereverse.ereverseauctionsystem.security;

import com.maharishi.may.ereverse.ereverseauctionsystem.domain.Account;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService
{
    @Value("${auction.security.jwt.key}")
    private String key;
    public String generateToken(Account user)
    {
        String jwt= Jwts.builder()
            .setIssuer("e-reverse-auction")
            .setIssuedAt(new Date())
            .claim("username",user.getUserName())
            .claim("role","user")
            .signWith(SignatureAlgorithm.HS256,key.getBytes())
            .compact();
        return jwt;
    }
}
