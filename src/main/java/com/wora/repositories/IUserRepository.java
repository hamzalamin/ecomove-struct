package com.wora.repositories;

import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.entities.User;

import java.util.Optional;

public interface IUserRepository {

    void register(CreateRegisterDto dto);
    Optional<User> login(String email, String name);

}
