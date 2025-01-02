package com.example.studentenrollment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentenrollment.model.Mahasiswa;
import com.example.studentenrollment.model.MataKuliah;
import com.example.studentenrollment.model.MataKuliahMahasiswa;
import com.example.studentenrollment.repository.MataKuliahRepository;

/**
 * Service layer for managing {@link MataKuliah} entities.
 * 
 * <p>This service provides business logic for creating, retrieving, updating, and deleting {@link MataKuliah}
 * entities. It also handles relationships between {@link MataKuliah} and {@link Mahasiswa} entities.</p>
 */
@Service
public class MataKuliahService {

    @Autowired
    private MataKuliahRepository mataKuliahRepository;

    /**
     * Creates a new {@link MataKuliah} entity.
     * 
     * @param mataKuliah The {@link MataKuliah} entity to be created.
     * @return The created {@link MataKuliah} entity.
     */
    public MataKuliah createMataKuliah(MataKuliah mataKuliah) {
        return mataKuliahRepository.save(mataKuliah);
    }

    /**
     * Retrieves all {@link MataKuliah} entities.
     * 
     * @return A list of all {@link MataKuliah} entities.
     */
    public List<MataKuliah> getAllMataKuliah() {
        return mataKuliahRepository.findAll();
    }

    /**
     * Retrieves a {@link MataKuliah} entity by its ID.
     * 
     * @param id The ID of the {@link MataKuliah} entity to retrieve.
     * @return The {@link MataKuliah} entity with the given ID.
     * @throws RuntimeException if no {@link MataKuliah} is found for the provided ID.
     */
    public MataKuliah getMataKuliahById(Long id) {
        return mataKuliahRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mata Kuliah tidak ditemukan dengan ID: " + id));
    }

    /**
     * Retrieves a {@link MataKuliah} entity along with its associated {@link Mahasiswa} entities.
     * 
     * <p>This method retrieves the {@link MataKuliah} entity and populates it with a list of
     * {@link Mahasiswa} entities that are associated with this {@link MataKuliah} through the
     * {@link MataKuliahMahasiswa} relationship.</p>
     * 
     * @param id The ID of the {@link MataKuliah} entity to retrieve.
     * @return The {@link MataKuliah} entity with associated {@link Mahasiswa} entities.
     * @throws RuntimeException if no {@link MataKuliah} is found for the provided ID.
     */
    public MataKuliah getMataKuliahWithDetails(Long id) {
        MataKuliah mataKuliah = mataKuliahRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("MataKuliah not found with ID: " + id));

        // Retrieve all mahasiswa related to this mataKuliah
        List<Mahasiswa> mahasiswaList = mataKuliah.getMataKuliahMahasiswa().stream()
                .map(MataKuliahMahasiswa::getMahasiswa)
                .collect(Collectors.toList());

        // Set the related mahasiswa list into mataKuliah
        mataKuliah.setMahasiswa(mahasiswaList);

        return mataKuliah;
    }

    /**
     * Updates an existing {@link MataKuliah} entity.
     * 
     * @param id The ID of the {@link MataKuliah} entity to update.
     * @param updatedMataKuliah The {@link MataKuliah} entity containing the updated values.
     * @return The updated {@link MataKuliah} entity.
     * @throws RuntimeException if the {@link MataKuliah} entity is not found for the provided ID.
     */
    public MataKuliah updateMataKuliah(Long id, MataKuliah updatedMataKuliah) {
        MataKuliah mataKuliah = getMataKuliahById(id);
        mataKuliah.setNama(updatedMataKuliah.getNama());
        mataKuliah.setKode(updatedMataKuliah.getKode());
        mataKuliah.setSks(updatedMataKuliah.getSks());
        return mataKuliahRepository.save(mataKuliah);
    }

    /**
     * Deletes a {@link MataKuliah} entity by its ID.
     * 
     * @param id The ID of the {@link MataKuliah} entity to delete.
     * @throws RuntimeException if the {@link MataKuliah} entity is not found for the provided ID.
     */
    public void deleteMataKuliah(Long id) {
        mataKuliahRepository.deleteById(id);
    }
}
