package com.example.homesvc.domain.mongo;

import com.example.homesvc.domain.enums.Role;
import com.example.homesvc.domain.enums.UserTier;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.Set;

@Document("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String username;
    //@JsonIgnore
    //private String passwordHash;
    private Set<Role> roles;
    private boolean delete;
    private Boolean enabled;
    @Version
    private Long version;
    private UserTier tier;

    @CreatedDate
    private Instant createdAt;
    @LastModifiedDate
    private Instant updatedAt;
    @CreatedBy
    private String createdBy;
    @LastModifiedBy
    private String updatedBy;
}
