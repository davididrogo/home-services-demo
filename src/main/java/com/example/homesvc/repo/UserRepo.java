package com.example.homesvc.repo;

import com.example.homesvc.domain.User;
import java.util.*;

public interface UserRepo {
    Optional<User> findById(Long id);
    void save(User u);
    java.util.List<User> findAll();
}
