package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Controller;

import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.LoginRequest;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.LoginResponse;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.RegisterRequest;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Security.JwtUtil;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    public AuthController(UserService userService, JwtUtil jwtUtil, PasswordEncoder encoder) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.encoder = encoder;
    }

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        var auth = userService.login(request); // will modify below to use BCrypt
        if (!auth.isSuccess()) return ResponseEntity.status(401).body(auth);
        String token = jwtUtil.generateToken(request.getUsername());
        return ResponseEntity.ok(new LoginResponse(true, token)); // token in message
    }
}
