package com.drugstore.Service;

import java.util.List;
import java.util.Optional;

import com.drugstore.DTO.LoginRequestDTO;
import com.drugstore.DTO.LoginResponseDTO;
import com.drugstore.DTO.UserRequestDTO;
import com.drugstore.DTO.UserResponseDTO;
import com.drugstore.Model.User;


public interface UserService {

    String registerUser(UserRequestDTO userDto);

    void saveRegisteredUser(User user);

    boolean isEmailExists(String email);

    UserResponseDTO getById(Long id);

    List<UserResponseDTO> getAll();

    UserResponseDTO updateUser(long id, UserRequestDTO dto);

    void deleteUser(Long id);

    LoginResponseDTO login(LoginRequestDTO dto);

    User findByUsername(String username);

    Optional<User> getUserByEmail(String email);

    Optional<User> validateUserCredentials(String email, String rawPassword);
}

