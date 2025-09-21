package com.example.homesvc.dto.mapstruct;

import com.example.homesvc.api.dto.UserRes;
import com.example.homesvc.domain.mongo.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserRes toRes(User u);
}
