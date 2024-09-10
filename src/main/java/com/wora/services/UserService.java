package com.wora.services;

import com.wora.models.dtos.CreateLoginDto;
import com.wora.models.dtos.CreateRegisterDto;
import com.wora.models.dtos.CreateUserDto;
import com.wora.models.entities.AuthenticatedUser;
import com.wora.models.entities.User;
import com.wora.repositories.IUserRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
           Optional<User> userInfo = repository.login(dto.email(), dto.name());
           if (userInfo.isPresent()){
               AuthenticatedUser.setAuthenticatedUser(userInfo.get());
               User user = userInfo.get();
               if (user.getName().equals(dto.name()) && user.getEmail().equals(dto.email())){
                   return Optional.of(user);
               }
           }
        return Optional.empty();
    }

    @Override
    public User update(CreateUserDto user, UUID id){

        if (AuthenticatedUser.isAuthentified()) {
            User authenticatedUser = AuthenticatedUser.getAuthenticatedUser();

            authenticatedUser.setName(user.name());
            authenticatedUser.setLastName(user.lastName());
            authenticatedUser.setEmail(user.email());
            authenticatedUser.setPhone_Number(user.phoneNumber());

            User updatedUser = repository.update(authenticatedUser, authenticatedUser.getId());

            AuthenticatedUser.setAuthenticatedUser(updatedUser);

            System.out.println("User updated successfully: " + updatedUser.getName() + ", " + updatedUser.getEmail());

            return updatedUser;
        } else {
            System.out.println("No authenticated user to update.");
            throw new RuntimeException("No authenticated user to update.");
        }
    }

    @Override
    public List<User> searchUsers(String email, String name) throws SQLException {
        return repository.search(email, name);
    }
}
