package com.wora.repositories;

import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.entities.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IUserRepository {

    void register(CreateRegisterDto dto);
    Optional<User> login(String email, String name);
    List<User> search(String email, String name) throws SQLException;
    Optional<User> findById(UUID userId);
    User update(User user, UUID id);
}
