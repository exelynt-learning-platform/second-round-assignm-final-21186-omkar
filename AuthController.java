package com.example.escontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.entity.User;
import com.example.security.JwtUtil;
import com.example.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private JwtUtil jwtUtil;

     private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

     @PostMapping("/register")
    public String register(@RequestBody User user) {
        user.setPassword(encoder.encode(user.getPassword())); // 🔥 FIX
        service.save(user);
        return "User registered successfully";
    }

     @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = service.findByUsername(user.getUsername());

        if (dbUser == null) {
            throw new RuntimeException("User not found");
        }

         if (encoder.matches(user.getPassword(), dbUser.getPassword())) {
            return jwtUtil.generateToken(dbUser.getUsername());
        } else {
            throw new RuntimeException("Invalid password");
        }
    }
}