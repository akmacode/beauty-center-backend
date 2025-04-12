package com.beautycenter.management.auth.interfaces.rest;

import com.beautycenter.management.auth.application.dto.AuthResponseDto;
import com.beautycenter.management.auth.application.dto.LoginRequestDto;
import com.beautycenter.management.auth.application.dto.RegisterRequestDto;
import com.beautycenter.management.auth.application.mapper.AuthenticationMapper;
import com.beautycenter.management.auth.domain.model.AuthToken;
import com.beautycenter.management.auth.domain.model.Credentials;
import com.beautycenter.management.auth.domain.model.RegistrationRequest;
import com.beautycenter.management.auth.domain.service.AuthenticationService;
import com.beautycenter.management.auth.domain.service.RegistrationService;
import com.beautycenter.management.domain.model.User;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

/**
 * REST controller for authentication endpoints.
 * This interface handles the HTTP requests related to authentication.
 */
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationService authenticationService;
    private final RegistrationService registrationService;
    private final AuthenticationMapper mapper;

    /**
     * Login endpoint.
     *
     * @param loginRequest the login request
     * @return authentication response with token
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        log.info("Processing login request for username: {}", loginRequest.getUsername());
        
        try {
            // Map to domain model
            Credentials credentials = mapper.toCredentials(loginRequest);
            
            // Authenticate user
            Optional<User> userOpt = authenticationService.authenticate(credentials);
            
            if (userOpt.isEmpty()) {
                log.warn("Authentication failed for username: {}", loginRequest.getUsername());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            
            User user = userOpt.get();
            log.debug("Authentication successful for user: {}", user.getUsername());
            
            // Generate JWT token
            AuthToken token = authenticationService.generateToken(user);
            
            // Create response
            AuthResponseDto response = mapper.toAuthResponseDto(token, user);
            
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Login error: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Login failed");
        }
    }

    /**
     * Registration endpoint.
     *
     * @param registerRequest the registration request
     * @return authentication response with token
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponseDto> register(@Valid @RequestBody RegisterRequestDto registerRequest) {
        log.info("Processing registration request for username: {}", registerRequest.getUsername());
        
        try {
            // Map to domain model
            RegistrationRequest registrationRequest = mapper.toRegistrationRequest(registerRequest);
            
            // Register user
            User user = registrationService.register(registrationRequest);
            log.debug("Registration successful for user: {}", user.getUsername());
            
            // Generate JWT token
            AuthToken token = authenticationService.generateToken(user);
            
            // Create response
            AuthResponseDto response = mapper.toAuthResponseDto(token, user);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            log.warn("Registration failed: {}", e.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            log.error("Registration error: ", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Registration failed");
        }
    }

    /**
     * Check username availability.
     *
     * @param username the username to check
     * @return true if available, false otherwise
     */
    @GetMapping("/check-username/{username}")
    public ResponseEntity<Boolean> checkUsernameAvailability(@PathVariable String username) {
        log.info("Checking username availability: {}", username);
        boolean exists = registrationService.usernameExists(username);
        return ResponseEntity.ok(!exists);
    }

    /**
     * Check email availability.
     *
     * @param email the email to check
     * @return true if available, false otherwise
     */
    @GetMapping("/check-email/{email}")
    public ResponseEntity<Boolean> checkEmailAvailability(@PathVariable String email) {
        log.info("Checking email availability: {}", email);
        boolean exists = registrationService.emailExists(email);
        return ResponseEntity.ok(!exists);
    }

    /**
     * Validate token.
     *
     * @param token the token to validate
     * @return true if valid, false otherwise
     */
    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token) {
        log.info("Validating token");
        boolean isValid = authenticationService.validateToken(token);
        return ResponseEntity.ok(isValid);
    }
}