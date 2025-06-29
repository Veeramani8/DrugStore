package com.drugstore.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.drugstore.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    

}
