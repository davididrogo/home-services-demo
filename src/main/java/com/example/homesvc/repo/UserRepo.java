package com.example.homesvc.repo;

import com.example.homesvc.domain.mongo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface UserRepo extends MongoRepository<User, String> {
    Optional<User> findByIdAndDeleteFalse(String id);
    Optional<User> findByEmailAndDeleteFalse(String email);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
    @Query("{'deleted':false, $or: [{'email': {$regex:?0,$options:'i'}}, {'username': {$regex:?0, $options: 'i'}} ] }")
    Page<User> search(String q, Pageable pageable);
}
