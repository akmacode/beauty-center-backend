package com.beautycenter.management.interfaces.web;

import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.service.AuthService;
import com.beautycenter.management.interfaces.web.dto.JwtResponse;
import com.beautycenter.management.interfaces.web.dto.LoginRequest;
import com.beautycenter.management.interfaces.web.dto.RegisterRequest;
import com.beautycenter.management.interfaces.web.dto.UserDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for authentication endpoints.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Login endpoint
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.authenticate(loginRequest.getUsername(), loginRequest.getPassword())
                .map(user -> {
                    String jwt = authService.generateToken(user);
                    JwtResponse response = new JwtResponse(
                            jwt,
                            user.getId(),
                            user.getUsername(),
                            user.getEmail(),
                            user.getRoles()
                    );
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Error: Invalid username or password!"));
    }

    /**
     * Register endpoint
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest) {
        // Check if username already exists
        if (authService.authenticate(registerRequest.getUsername(), "dummy").isPresent()) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        }

        // Create new user
        User user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(registerRequest.getPassword())
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .phoneNumber(registerRequest.getPhoneNumber())
                .roles(registerRequest.getRoles())
                .build();

        User registeredUser = authService.register(user);
        
        // Map to DTO
        UserDto userDto = UserDto.builder()
                .id(registeredUser.getId())
                .username(registeredUser.getUsername())
                .email(registeredUser.getEmail())
                .firstName(registeredUser.getFirstName())
                .lastName(registeredUser.getLastName())
                .phoneNumber(registeredUser.getPhoneNumber())
                .active(registeredUser.isActive())
                .roles(registeredUser.getRoles())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(userDto);
    }
}