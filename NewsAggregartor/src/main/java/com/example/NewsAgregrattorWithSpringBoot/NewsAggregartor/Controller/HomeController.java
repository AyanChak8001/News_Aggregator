package com.example.NewsAgregrattorWithSpringBoot.NewsAggregartor.Controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HomeController {

//    @GetMapping("/")
//    public void redirectToSwagger(HttpServletResponse response) throws IOException {
//        response.sendRedirect("/swagger-ui.html");
//    }
//    @GetMapping("/")
    public String home() {
        return "index.html";
    }
}
