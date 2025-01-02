package com.example.studentenrollment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentenrollment.model.Mahasiswa;
import com.example.studentenrollment.model.MataKuliah;
import com.example.studentenrollment.model.MataKuliahMahasiswa;
import com.example.studentenrollment.repository.MahasiswaRepository;


/**
 * Service layer for managing {@link Mahasiswa} entities.
 * 
 * <p>This service class provides business logic for creating, retrieving, updating, and deleting
 * {@link Mahasiswa} records in the database. It interacts with the {@link MahasiswaRepository} for CRUD operations.</p>
 */
@Service
public class MahasiswaService {

    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    /**
     * Creates a new {@link Mahasiswa} entity.
     * 
     * <p>This method saves the provided {@link Mahasiswa} instance into the database.</p>
     * 
     * @param mahasiswa The {@link Mahasiswa} entity to create.
     * @return The saved {@link Mahasiswa} entity.
     */
    public Mahasiswa createMahasiswa(Mahasiswa mahasiswa) {
        return mahasiswaRepository.save(mahasiswa);
    }

    /**
     * Retrieves all {@link Mahasiswa} entities from the database.
     * 
     * @return A list of all {@link Mahasiswa} entities.
     */
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepository.findAll();
    }

    /**
     * Retrieves the list of {@link MataKuliah} entities associated with a given NIM (student ID).
     * 
     * <p>This method finds a {@link Mahasiswa} by their NIM and returns a list of {@link MataKuliah}
     * associated with that student through the {@link MataKuliahMahasiswa} relationship.</p>
     * 
     * @param nim The NIM of the {@link Mahasiswa} to search for.
     * @return A list of {@link MataKuliah} entities associated with the given NIM.
     * @throws RuntimeException if no {@link Mahasiswa} is found with the given NIM.
     */
    public List<MataKuliah> getMataKuliahByNim(String nim) {
        Mahasiswa mahasiswa = mahasiswaRepository.findByNim(nim)
                .orElseThrow(() -> new RuntimeException("Mahasiswa tidak ditemukan dengan NIM: " + nim));

        // Retrieve MataKuliah through MataKuliahMahasiswa relationship
        return mahasiswa.getMataKuliahMahasiswa().stream()
                .map(MataKuliahMahasiswa::getMataKuliah)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a {@link Mahasiswa} entity by its ID.
     * 
     * <p>If no entity is found with the given ID, a {@link RuntimeException} is thrown.</p>
     * 
     * @param id The ID of the {@link Mahasiswa} entity to retrieve.
     * @return The {@link Mahasiswa} entity with the given ID.
     * @throws RuntimeException if no {@link Mahasiswa} entity is found for the provided ID.
     */
    public Mahasiswa getMahasiswaById(Long id) {
        return mahasiswaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mahasiswa tidak ditemukan dengan ID: " + id));
    }

    /**
     * Retrieves a {@link Mahasiswa} entity with additional details, such as associated {@link MataKuliah}
     * and related grades (UTS, UAS, Kuis, total, grade).
     * 
     * <p>This method fetches a {@link Mahasiswa} and populates the list of {@link MataKuliah} with
     * the corresponding grades and other academic data.</p>
     * 
     * @param id The ID of the {@link Mahasiswa} entity to retrieve.
     * @return The {@link Mahasiswa} entity with additional details, including associated {@link MataKuliah}.
     * @throws RuntimeException if no {@link Mahasiswa} entity is found for the provided ID.
     */
    public Mahasiswa getMahasiswaWithDetails(Long id) {
        Mahasiswa mahasiswa = mahasiswaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mahasiswa not found with ID: " + id));

        // Map MataKuliahMahasiswa to add grade details to MataKuliah
        List<MataKuliah> mataKuliahList = mahasiswa.getMataKuliahMahasiswa().stream()
                .map(mkm -> {
                    MataKuliah mk = mkm.getMataKuliah(); // Get MataKuliah data
                    // Set additional properties from MataKuliahMahasiswa
                    mk.setUts(mkm.getUts());
                    mk.setUas(mkm.getUas());
                    mk.setKuis(mkm.getKuis());
                    mk.setTotal(mkm.getTotal());
                    mk.setGrade(mkm.getGrade());
                    return mk;
                }).collect(Collectors.toList());

        mahasiswa.setMataKuliah(mataKuliahList);

        return mahasiswa;
    }

    /**
     * Updates an existing {@link Mahasiswa} entity.
     * 
     * <p>This method updates the properties of an existing {@link Mahasiswa} entity identified by its ID.
     * If the entity is not found, an exception is thrown. The updated data is saved to the database.</p>
     * 
     * @param id The ID of the {@link Mahasiswa} entity to update.
     * @param updatedMahasiswa The updated {@link Mahasiswa} entity data.
     * @return The updated {@link Mahasiswa} entity.
     * @throws RuntimeException if no {@link Mahasiswa} entity is found for the provided ID.
     */
    public Mahasiswa updateMahasiswa(Long id, Mahasiswa updatedMahasiswa) {
        Mahasiswa mahasiswa = getMahasiswaById(id);
        mahasiswa.setUsername(updatedMahasiswa.getUsername());
        mahasiswa.setPassword(updatedMahasiswa.getPassword());
        mahasiswa.setNim(updatedMahasiswa.getNim());
        mahasiswa.setProdi(updatedMahasiswa.getProdi());
        return mahasiswaRepository.save(mahasiswa);
    }

    /**
     * Deletes a {@link Mahasiswa} entity by its ID.
     * 
     * <p>This method removes the {@link Mahasiswa} entity with the specified ID from the database.</p>
     * 
     * @param id The ID of the {@link Mahasiswa} entity to delete.
     */
    public void deleteMahasiswa(Long id) {
        mahasiswaRepository.deleteById(id);
    }
}
