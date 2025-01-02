package com.example.studentenrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentenrollment.model.MataKuliahMahasiswa;
import com.example.studentenrollment.repository.MataKuliahMahasiswaRepository;
import com.example.studentenrollment.service.MataKuliahMahasiswaService;

/**
 * REST controller for managing the relationship between Students and Courses (Mata Kuliah).
 * 
 * <p>This controller provides APIs for the following operations:
 * - Assigning a Student to a Course
 * - Deleting the relationship between a Student and a Course
 * - Retrieving details of the relationship
 * - Updating a Student's grades for a specific Course</p>
 */
@RestController
@RequestMapping("/api/matakuliahmahasiswa")
public class MataKuliahMahasiswaController {
    
    @Autowired
    private MataKuliahMahasiswaService service;

    @Autowired
    private MataKuliahMahasiswaRepository mataKuliahMahasiswaRepository;

    /**
     * Assigns a Student to a Course.
     *
     * @param mahasiswaId ID of the Student
     * @param mataKuliahId ID of the Course
     * @return ResponseEntity containing the created MataKuliahMahasiswa object
     */
    @PostMapping("/assign")
    public ResponseEntity<MataKuliahMahasiswa> assignMataKuliahToMahasiswa(
            @RequestParam Long mahasiswaId,
            @RequestParam Long mataKuliahId) {
        System.out.println("Assigning Student ID: " + mahasiswaId + " to Course ID: " + mataKuliahId);
        MataKuliahMahasiswa assigned = service.assignMataKuliahToMahasiswa(mahasiswaId, mataKuliahId);
        return ResponseEntity.ok(assigned);
    }

    /**
     * Deletes the relationship between a Student and a Course based on their IDs.
     *
     * @param mahasiswaId ID of the Student
     * @param mataKuliahId ID of the Course
     * @return ResponseEntity with no content (204 No Content)
     */
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteMataKuliahMahasiswaByParams(
            @RequestParam Long mahasiswaId,
            @RequestParam Long mataKuliahId) {
        service.deleteMataKuliahMahasiswaByParams(mahasiswaId, mataKuliahId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves the relationship details between a Student and a Course by its ID.
     *
     * @param id ID of the MataKuliahMahasiswa relationship
     * @return ResponseEntity containing the MataKuliahMahasiswa object with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MataKuliahMahasiswa> getMataKuliahMahasiswaById(@PathVariable Long id) {
        MataKuliahMahasiswa mataKuliahMahasiswa = service.getById(id);
        return ResponseEntity.ok(mataKuliahMahasiswa);
    }

    /**
     * Updates a Student's grades for a specific Course.
     *
     * @param id ID of the MataKuliahMahasiswa relationship
     * @param uts UTS grade
     * @param uas UAS grade
     * @param kuis Quiz grade
     * @return ResponseEntity containing a confirmation message that the grades have been updated
     */
    @PostMapping("/update-nilai")
    public ResponseEntity<?> updateNilai(@RequestParam Long id, @RequestParam int uts, @RequestParam int uas, @RequestParam int kuis) {
        MataKuliahMahasiswa matkulMahasiswa = mataKuliahMahasiswaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Data not found"));

        matkulMahasiswa.setUts(uts);
        matkulMahasiswa.setUas(uas);
        matkulMahasiswa.setKuis(kuis);
        
        // Automatically calculate total score and grade
        mataKuliahMahasiswaRepository.save(matkulMahasiswa);
        return ResponseEntity.ok("Score Updated !!.");
    }
}