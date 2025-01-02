package com.example.studentenrollment.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentenrollment.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
