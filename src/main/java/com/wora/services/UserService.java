package com.wora.services;

import com.wora.models.dtos.CreateLoginDto;
import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.entities.User;
import com.wora.repositories.UserRepository;

import java.util.Optional;

public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void registerUser(CreateRegisterDto dto) {
        repository.register(dto);
    }

    public Optional<User> loginUser(CreateLoginDto dto) {
        return repository.login(dto.email(), dto.name());
    }
}
