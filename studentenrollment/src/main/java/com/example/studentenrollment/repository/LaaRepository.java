package com.example.studentenrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.studentenrollment.model.Laa;

public interface LaaRepository extends JpaRepository<Laa, Long> {
    Optional<Laa> findByUsername(String username);
}

