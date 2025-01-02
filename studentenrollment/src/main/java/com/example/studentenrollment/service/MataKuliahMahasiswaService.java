package com.example.studentenrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentenrollment.model.Mahasiswa;
import com.example.studentenrollment.model.MataKuliah;
import com.example.studentenrollment.model.MataKuliahMahasiswa;
import com.example.studentenrollment.repository.MahasiswaRepository;
import com.example.studentenrollment.repository.MataKuliahMahasiswaRepository;
import com.example.studentenrollment.repository.MataKuliahRepository;

/**
 * Service layer for managing the {@link MataKuliahMahasiswa} relationship.
 * 
 * <p>This service class provides business logic for managing the relationship between
 * {@link Mahasiswa} and {@link MataKuliah} entities. It handles assigning, removing, and retrieving
 * {@link MataKuliahMahasiswa} instances from the database.</p>
 */
@Service
public class MataKuliahMahasiswaService {

    @Autowired
    private MataKuliahMahasiswaRepository mataKuliahMahasiswaRepository;

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    @Autowired
    private MataKuliahRepository mataKuliahRepository;

    /**
     * Assigns a {@link MataKuliah} to a {@link Mahasiswa}.
     * 
     * <p>This method creates a new {@link MataKuliahMahasiswa} relationship between the given
     * {@link Mahasiswa} and {@link MataKuliah} by their respective IDs.</p>
     * 
     * @param mahasiswaId The ID of the {@link Mahasiswa} to assign.
     * @param mataKuliahId The ID of the {@link MataKuliah} to assign.
     * @return The saved {@link MataKuliahMahasiswa} relationship.
     * @throws RuntimeException if either the {@link Mahasiswa} or {@link MataKuliah} is not found.
     */
    public MataKuliahMahasiswa assignMataKuliahToMahasiswa(Long mahasiswaId, Long mataKuliahId) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(mahasiswaId)
                .orElseThrow(() -> new RuntimeException("Mahasiswa not found with ID: " + mahasiswaId));

        MataKuliah mataKuliah = mataKuliahRepository.findById(mataKuliahId)
                .orElseThrow(() -> new RuntimeException("MataKuliah not found with ID: " + mataKuliahId));

        MataKuliahMahasiswa mataKuliahMahasiswa = new MataKuliahMahasiswa();
        mataKuliahMahasiswa.setMahasiswa(mahasiswa);
        mataKuliahMahasiswa.setMataKuliah(mataKuliah);

        return mataKuliahMahasiswaRepository.save(mataKuliahMahasiswa);
    }

    /**
     * Deletes a {@link MataKuliahMahasiswa} relationship based on the given {@link Mahasiswa} and
     * {@link MataKuliah} IDs.
     * 
     * <p>This method removes the relationship between a {@link Mahasiswa} and a {@link MataKuliah}
     * if it exists in the database.</p>
     * 
     * @param mahasiswaId The ID of the {@link Mahasiswa}.
     * @param mataKuliahId The ID of the {@link MataKuliah}.
     * @throws RuntimeException if the relationship is not found for the given IDs.
     */
    public void deleteMataKuliahMahasiswaByParams(Long mahasiswaId, Long mataKuliahId) {
        MataKuliahMahasiswa relation = mataKuliahMahasiswaRepository.findByMahasiswaIdAndMataKuliahId(mahasiswaId, mataKuliahId)
                .orElseThrow(() -> new RuntimeException("Relasi not found for Mahasiswa ID: " + mahasiswaId + " and MataKuliah ID: " + mataKuliahId));
        mataKuliahMahasiswaRepository.delete(relation);
    }

    /**
     * Retrieves a {@link MataKuliahMahasiswa} entity by its ID.
     * 
     * <p>If no {@link MataKuliahMahasiswa} entity is found with the provided ID, a {@link RuntimeException}
     * is thrown.</p>
     * 
     * @param id The ID of the {@link MataKuliahMahasiswa} entity to retrieve.
     * @return The {@link MataKuliahMahasiswa} entity with the given ID.
     * @throws RuntimeException if no {@link MataKuliahMahasiswa} is found for the provided ID.
     */
    public MataKuliahMahasiswa getById(Long id) {
        return mataKuliahMahasiswaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MataKuliahMahasiswa not found with ID: " + id));
    }
}
