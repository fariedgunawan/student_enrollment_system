package com.example.studentenrollment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.studentenrollment.model.Mahasiswa;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Long> {
    // Cari mahasiswa berdasarkan username
    Optional<Mahasiswa> findByNim(String nim);
    Optional<Mahasiswa> findByUsername(String username);
}
