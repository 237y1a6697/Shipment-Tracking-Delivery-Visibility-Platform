package com.shiptrack.security;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.shiptrack.user.UserRepository;
@Component
public class JwtFilter extends OncePerRequestFilter {
  private final JwtService jwt; private final UserRepository users;
  public JwtFilter(JwtService jwt,UserRepository users){this.jwt=jwt;this.users=users;}
  protected void doFilterInternal(HttpServletRequest req,HttpServletResponse res,FilterChain chain)throws ServletException,IOException{
    String h=req.getHeader("Authorization");
    if(h!=null&&h.startsWith("Bearer ")){
      try{
        String email=jwt.extractEmail(h.substring(7));
        users.findByEmail(email).ifPresent(u->SecurityContextHolder.getContext().setAuthentication(
          new UsernamePasswordAuthenticationToken(email,null,java.util.List.of(new SimpleGrantedAuthority("ROLE_"+u.getRole())))));
      }catch(Exception ignored){}
    }
    chain.doFilter(req,res);
  }
}