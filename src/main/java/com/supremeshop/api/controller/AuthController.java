package com.supremeshop.api.controller;

import com.supremeshop.api.model.User;
import com.supremeshop.api.security.JwtUtil;
import com.supremeshop.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user.getUsername(), user.getPassword(), "USER");
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody User user) {
        User foundUser = userService.findByUsername(user.getUsername());
        
        if (foundUser == null || !passwordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
            throw new RuntimeException("Usuario o contraseña incorrectos");
        }

        String token = jwtUtil.generateToken(foundUser.getUsername(), foundUser.getRole());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return response;
    }
}
