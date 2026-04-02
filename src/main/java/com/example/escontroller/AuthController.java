package com.example.escontroller;

 
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        service.save(user);
        return "User registered";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User dbUser = service.findByUsername(user.getUsername());

        if (dbUser == null || !dbUser.getPassword().equals(user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}
