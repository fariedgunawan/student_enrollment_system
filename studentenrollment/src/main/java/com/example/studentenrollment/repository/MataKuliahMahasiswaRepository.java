package com.example.studentenrollment.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.studentenrollment.model.MataKuliahMahasiswa;

public interface MataKuliahMahasiswaRepository extends JpaRepository<MataKuliahMahasiswa, Long> {
    Optional<MataKuliahMahasiswa> findByMahasiswaIdAndMataKuliahId(Long mahasiswaId, Long mataKuliahId);
}
