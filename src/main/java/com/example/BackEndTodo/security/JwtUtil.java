package com.example.BackEndTodo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Component
public class JwtUtil {
    private final static String SECRET_KEY = "294A404E635266546A576E5A7234753778214125442A472D4B6150645367566B";

    public String extractUserName(String token){return extractClaim(token, Claims::getSubject);}

    public String generateToken(UserDetails userDetails) {return generateToken(new HashMap<>(),userDetails);}

    public String generateToken(
            Map<String, Objects> extraClaims,
            UserDetails userDetails
    ){
      return Jwts
              .builder()
              .setClaims(extraClaims)
              .setSubject(userDetails.getUsername())
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 2_5922_000L * 1_000))
              .signWith(getSignInKey(), SignatureAlgorithm.HS256)
              .compact();
    }

    public boolean isTokenValid(String token,UserDetails userDetails){
        final String username = extractUserName(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpred(token);
    }

    private boolean isTokenExpred(String token){return ExtractExpiration(token).before(new Date());}

    private Date ExtractExpiration(String token){return extractClaim(token,Claims::getExpiration);}

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token){
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(){
        byte[] keyByter = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyByter);
    }

        }
