package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Controller;

import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.RegisterRequest;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.LoginRequest;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Dto.LoginResponse;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Model.User;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Register
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }

    // Login
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    // Get user
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUser(username));
    }

    // Full profile update
    @PutMapping("/{username}")
    public ResponseEntity<User> updateUser(@PathVariable String username,
                                           @RequestBody RegisterRequest updatedUser) {
        return ResponseEntity.ok(userService.updateUser(username, updatedUser));
    }

    // Delete
    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return ResponseEntity.ok("User deleted successfully");
    }
}
