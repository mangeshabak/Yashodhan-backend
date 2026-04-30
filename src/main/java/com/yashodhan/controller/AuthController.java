package com.yashodhan.controller;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yashodhan.dto.LoginRequest;
import com.yashodhan.entity.User;
import com.yashodhan.service.JwtService;
import com.yashodhan.service.UserService;

@RestController
@RequestMapping("/auth")
//@CrossOrigin("*")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        User user = userService.login(request.getUsername());

        if (user == null || !user.getPassword().equals(request.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid Username or Password");
        }

        String token = jwtService.generateToken(user.getUsername(), user.getRole());

        // 👇 send role also
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("role", user.getRole());
        response.put("userId", user.getId());
        response.put("employeename", user.getUsername());
        response.put("id", user.getId());

        return ResponseEntity.ok(response);
    }
}