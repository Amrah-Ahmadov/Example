package com.example.callcenterforloanproject.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;

@Service
public class JWTManager {
    private Date issuedAt;
    private int expiration = 5*60*1000;
    @Value("${jwt.secret}")
    private String secret;


    public String generateJwt(String userName){
        issuedAt = new Date(System.currentTimeMillis());
        return Jwts.builder()
                .setSubject(userName)
                .setIssuer("Expressbank.az")
                .setIssuedAt(issuedAt)
                .setExpiration(new Date(issuedAt.getTime() + expiration))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
    public boolean validateToken(String token){
        if(getUserNameFromToken(token) != null && getExpirationDate(token).after(new Date(System.currentTimeMillis()))){
            return true;
        }
        return false;
    }
    public String getUserNameFromToken(String token){
        Claims claims = getClaims(token);
        return claims.getSubject();
    }
    public Date getExpirationDate(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration();
    }
    private Claims getClaims(String token){
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }
}
