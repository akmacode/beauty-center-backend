package com.beautycenter.management.auth.application.service;

import com.beautycenter.management.auth.domain.model.AuthToken;
import com.beautycenter.management.auth.domain.model.Credentials;
import com.beautycenter.management.auth.domain.service.AuthenticationService;
import com.beautycenter.management.auth.infrastructure.security.JwtTokenProvider;
import com.beautycenter.management.domain.model.User;
import com.beautycenter.management.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implementation of the AuthenticationService interface.
 * This service handles authentication operations in the application.
 */
@Service
@AllArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtTokenProvider tokenProvider;

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<User> authenticate(Credentials credentials) {
        try {
            // Validate credentials
            if (!credentials.isValid()) {
                log.warn("Invalid authentication credentials provided");
                return Optional.empty();
            }

            // Attempt authentication
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword())
            );

            // Set authentication in security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Find and return the user
            return userRepository.findByUsername(credentials.getUsername());
        } catch (BadCredentialsException e) {
            log.warn("Failed authentication attempt for username: {}", credentials.getUsername());
            return Optional.empty();
        } catch (Exception e) {
            log.error("Authentication error: ", e);
            return Optional.empty();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AuthToken generateToken(User user) {
        return tokenProvider.generateToken(user);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validateToken(String token) {
        return tokenProvider.validateToken(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUsernameFromToken(String token) {
        return tokenProvider.getUsernameFromToken(token);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long getUserIdFromToken(String token) {
        return tokenProvider.getUserIdFromToken(token);
    }
}