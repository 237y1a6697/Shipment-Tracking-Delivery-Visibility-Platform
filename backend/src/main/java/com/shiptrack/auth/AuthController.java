package com.shiptrack.auth;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.shiptrack.security.JwtService;
import com.shiptrack.user.*;
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins="http://localhost:5173")
public class AuthController {
  private final UserRepository users; private final PasswordEncoder encoder; private final JwtService jwt;
  public AuthController(UserRepository users,PasswordEncoder encoder,JwtService jwt){this.users=users;this.encoder=encoder;this.jwt=jwt;}
  @PostMapping("/register")
  public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest r){
    if(users.existsByEmail(r.email())) return ResponseEntity.badRequest().body(java.util.Map.of("message","Email is already registered"));
    User u=new User(r.name(),r.email(),encoder.encode(r.password())); users.save(u);
    return ResponseEntity.status(HttpStatus.CREATED).body(new AuthResponse(jwt.generate(u.getEmail()),u.getName(),u.getEmail(),u.getRole().name()));
  }
  @PostMapping("/login")
  public ResponseEntity<?> login(@Valid @RequestBody AuthRequest r){
    User u=users.findByEmail(r.email()).orElse(null);
    if(u==null||!encoder.matches(r.password(),u.getPassword())) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(java.util.Map.of("message","Invalid email or password"));
    return ResponseEntity.ok(new AuthResponse(jwt.generate(u.getEmail()),u.getName(),u.getEmail(),u.getRole().name()));
  }
}