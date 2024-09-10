package com.wora.services;

import com.wora.models.dtos.CreateLoginDto;
import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.entities.User;
import com.wora.repositories.IUserRepository;
import com.wora.repositories.UserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class UserService implements IUserService{
    private final IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void registerUser(CreateRegisterDto dto) {
        repository.register(dto);
    }

    @Override
    public Optional<User> loginUser(CreateLoginDto dto) {
        return repository.login(dto.email(), dto.name());
    }

    @Override
    public List<User> searchUsers(String email, String name) throws SQLException {
        return repository.search(email, name);
    }
}
