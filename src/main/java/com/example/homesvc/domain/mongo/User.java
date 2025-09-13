package com.example.homesvc.domain.mongo;

import com.example.homesvc.domain.enums.UserTier;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("users")
public class User {
    @Id
    public Long id;
    @Indexed(unique = true)
    public String email;
    public UserTier tier;
    public User(){}
    public User(Long id, String email, UserTier tier){
        this.id = id;
        this.email = email;
        this.tier = tier;
    }
}
