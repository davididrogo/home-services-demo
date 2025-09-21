package com.example.homesvc.api.dto;

import com.example.homesvc.domain.enums.Role;

import java.time.Instant;
import java.util.Set;

public record UserRes(String id,
                      String email,
                      String username,
                      Set<Role> roles,
                      Boolean enabled,
                      Instant createdAt,
                      Instant updatedAt,
                      Long version) {
}
