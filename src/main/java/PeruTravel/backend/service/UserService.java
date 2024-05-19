package PeruTravel.backend.service;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import PeruTravel.backend.Response.AuthResponse;
import PeruTravel.backend.model.Role;
import PeruTravel.backend.model.User;
import PeruTravel.backend.repository.UserRepository;
import PeruTravel.backend.request.LoginRequest;
import PeruTravel.backend.request.RegisterRequest;
import PeruTravel.backend.security.JwtService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
            .username(request.getUsername())
            .password(request.getPassword()) 
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .role(Role.USER)
            .build();
        userRepository.save(user);
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }
}
