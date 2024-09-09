package com.wora.services;

import com.wora.models.dtos.CreateLoginDto;
import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.entities.User;

import java.util.Optional;

public interface IUserService {

    void registerUser(CreateRegisterDto dto);
    Optional<User> loginUser(CreateLoginDto dto);
}
