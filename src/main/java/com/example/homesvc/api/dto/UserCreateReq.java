package com.example.homesvc.api.dto;

import com.example.homesvc.domain.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserCreateReq(
        @NotBlank
        @Email
        String email,
        @NotBlank
        @Size(min = 3, max = 40)
        String username,
        @Size(min = 8, max = 128)
        String password,
        Set<Role> roles
){}
