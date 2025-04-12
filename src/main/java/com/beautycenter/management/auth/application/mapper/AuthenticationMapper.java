package com.beautycenter.management.auth.application.mapper;

import com.beautycenter.management.auth.application.dto.AuthResponseDto;
import com.beautycenter.management.auth.application.dto.LoginRequestDto;
import com.beautycenter.management.auth.application.dto.RegisterRequestDto;
import com.beautycenter.management.auth.domain.model.AuthToken;
import com.beautycenter.management.auth.domain.model.Credentials;
import com.beautycenter.management.auth.domain.model.RegistrationRequest;
import com.beautycenter.management.domain.model.User;
import org.springframework.stereotype.Component;

/**
 * Mapper class for converting between DTOs and domain models in the authentication module.
 * This is an application service component that facilitates the transformation between layers.
 */
@Component
public class AuthenticationMapper {

    /**
     * Convert LoginRequestDto to Credentials.
     *
     * @param dto the login request DTO
     * @return credentials domain model
     */
    public Credentials toCredentials(LoginRequestDto dto) {
        return Credentials.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build();
    }

    /**
     * Convert RegisterRequestDto to RegistrationRequest.
     *
     * @param dto the register request DTO
     * @return registration request domain model
     */
    public RegistrationRequest toRegistrationRequest(RegisterRequestDto dto) {
        return RegistrationRequest.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .phoneNumber(dto.getPhoneNumber())
                .roles(dto.getRoles())
                .build();
    }

    /**
     * Convert AuthToken and User to AuthResponseDto.
     *
     * @param token the authentication token
     * @param user the user
     * @return authentication response DTO
     */
    public AuthResponseDto toAuthResponseDto(AuthToken token, User user) {
        return AuthResponseDto.builder()
                .token(token.getTokenValue())
                .type(token.getTokenType())
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .roles(user.getRoles())
                .build();
    }
}