package com.example.homesvc.api.dto;

import com.example.homesvc.domain.enums.Role;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record UserUpdateReq(
        @Size(min=3,max=40)
        String username,
        @Size(min=8,max=128)
        String password,
        Set<Role> roles,
        Boolean enabled
){}
