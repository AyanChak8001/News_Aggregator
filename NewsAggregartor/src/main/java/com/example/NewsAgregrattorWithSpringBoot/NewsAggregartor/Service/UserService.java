package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Service;

import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.RegisterRequest;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.LoginRequest;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.LoginResponse;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.PreferencesRequest;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Model.User;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Security.JwtUtil;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder encoder,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtil = jwtUtil;
    }

    // REGISTER
    public String register(RegisterRequest req) {
        var existing = userRepository.findByUsername(req.getUsername());
        if (existing.isPresent()) return "USERNAME_TAKEN";

        User u = new User();
        u.setUsername(req.getUsername());
        u.setPassword(encoder.encode(req.getPassword())); // HASH!
        u.setEmail(req.getEmail());
        u.setPreferences(req.getPreferences());
        userRepository.save(u);
        return "OK";
    }

    // LOGIN
    public LoginResponse login(LoginRequest req) {
        var opt = userRepository.findByUsername(req.getUsername());
        if (opt.isEmpty()) return new LoginResponse(false, null);
        User u = opt.get();
        if (!encoder.matches(req.getPassword(), u.getPassword())) {
            return new LoginResponse(false, null);
        }

        String token = jwtUtil.generateToken(u.getUsername());
        return new LoginResponse(true, token);  // Return JWT token here
    }


    // GET USER
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // UPDATE USER
    public User updateUser(String username, RegisterRequest updatedUser) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
            existingUser.setPassword(encoder.encode(updatedUser.getPassword()));
        }
        if (updatedUser.getEmail() != null) {
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPreferences() != null) {
            existingUser.setPreferences(updatedUser.getPreferences());
        }
        return userRepository.save(existingUser);
    }

    // DELETE USER
    public void deleteUser(String username) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(existingUser);
    }
}
