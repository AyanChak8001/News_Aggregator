package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Security;

import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Model.User;
import com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword()) // Must be encoded
                .authorities("ROLE_USER")
                .build();
    }
}
