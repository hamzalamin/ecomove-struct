package com.wora.services;

import com.wora.models.dtos.CreateLoginDto;
import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.dtos.CreateUserDto;
import com.wora.models.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserService {

    void registerUser(CreateRegisterDto dto);
    Optional<User> loginUser(CreateLoginDto dto);
    List<User> searchUsers(String email, String name) throws SQLException;
    User update(CreateUserDto user, UUID id);

}
