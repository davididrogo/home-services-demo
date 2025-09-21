package com.example.homesvc.api;

import com.example.homesvc.api.dto.UserCreateReq;
import com.example.homesvc.api.dto.UserRes;
import com.example.homesvc.domain.mongo.User;
import com.example.homesvc.dto.mapstruct.UserMapper;
import com.example.homesvc.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper m;

    @PostMapping
    public ResponseEntity<UserRes> create(@Valid @RequestBody UserCreateReq req, UriComponentsBuilder uri){
        var saved = userService.create(req);
        return ResponseEntity
                .created(uri.path("/api/v1/users/{id}").build(saved.getId()))
                .eTag("\"" + saved.getVersion()+"\"")
                .body(m.toRes(saved));
    }

}
