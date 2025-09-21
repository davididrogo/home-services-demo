package com.example.homesvc.service;

import com.example.homesvc.api.dto.UserCreateReq;
import com.example.homesvc.api.dto.UserUpdateReq;
import com.example.homesvc.common.error.ConflictException;
import com.example.homesvc.common.error.NotFoundException;
import com.example.homesvc.domain.enums.Role;
import com.example.homesvc.domain.mongo.User;
import com.example.homesvc.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.PasswordAuthentication;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo repo;
    //private final PasswordEncoder encoder;

    public User create(UserCreateReq req){
        if(repo.existsByEmail(req.email())) throw new ConflictException("email in use");
        if(repo.existsByUsername(req.username())) throw new ConflictException("username in use");
        var u = User.builder()
                .email(req.email())
                .username(req.username())
                //.passwordHash(encoder.encode(req.password()))
                .roles(Optional.ofNullable(req.roles()).orElse(Set.of(Role.USER)))
                .enabled(true)
                .delete(false)
                .build();
        return repo.save(u);
    }
    public User get(String id){
        return repo.findByIdAndDeleteFalse(id)
                .orElseThrow(NotFoundException::new);
    }
    @Transactional
    public User update(String id, Long ifMatch, UserUpdateReq req){
        var u = get(id);
        //if(!Objects.equals(u.getVersion(), ifMatch)) throw new PreconditionFailedException("stale version");
        if(req.username()!=null) u.setUsername(req.username());
        //if(req.password()!=null) u.setPasswordHash(encoder.encode(req.password()));
        if(req.roles()!=null) u.setRoles(req.roles());
        if(req.enabled()!=null) u.setEnabled(req.enabled());
        return repo.save(u);
    }
    public void softDelete(String id){
        var u = get(id);
        u.setDelete(true);
        repo.save(u);
    }
}
