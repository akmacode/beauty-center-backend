package com.beautycenter.management.application.service;

import com.beautycenter.management.domain.model.Role;
import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.repository.UserRepository;
import com.beautycenter.management.domain.service.AuthService;
import com.beautycenter.management.infrastructure.security.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the AuthService domain service.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return userRepository.findByUsername(username);
    }

    @Override
    public User register(User user) {
        // Encrypt password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // Set default role if not specified
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_CUSTOMER);
            user.setRoles(roles);
        }
        
        // Set default active status
        user.setActive(true);
        
        return userRepository.save(user);
    }

    @Override
    public String generateToken(User user) {
        return jwtUtils.generateToken(user);
    }

    @Override
    public boolean validateToken(String token) {
        return jwtUtils.validateToken(token);
    }

    @Override
    public String getUsernameFromToken(String token) {
        return jwtUtils.getUsernameFromToken(token);
    }
}