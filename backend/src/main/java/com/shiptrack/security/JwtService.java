package com.shiptrack.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class JwtService {
  private final Key key; private final long expiration;
  public JwtService(@Value("${app.jwt.secret}") String secret,@Value("${app.jwt.expiration}") long expiration){
    key=Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)); this.expiration=expiration;
  }
  public String generate(String email){
    Date now=new Date();
    return Jwts.builder().subject(email).issuedAt(now).expiration(new Date(now.getTime()+expiration)).signWith(key).compact();
  }
  public String extractEmail(String token){return Jwts.parser().verifyWith((javax.crypto.SecretKey)key).build().parseSignedClaims(token).getPayload().getSubject();}
  public boolean valid(String token,String email){
    try{return email.equals(extractEmail(token));}catch(Exception e){return false;}
  }
}