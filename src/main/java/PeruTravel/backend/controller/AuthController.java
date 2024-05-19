package PeruTravel.backend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import PeruTravel.backend.Response.AuthResponse;
import PeruTravel.backend.request.LoginRequest;
import PeruTravel.backend.request.RegisterRequest;
import PeruTravel.backend.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    
    private final UserService userService;
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(userService.login(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(userService.register(request));
    }


    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


}
