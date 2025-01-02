package com.example.studentenrollment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentenrollment.model.MataKuliah;
import com.example.studentenrollment.service.MataKuliahService;

/**
 * REST controller for managing Mata Kuliah data.
 * 
 * <p>This controller provides APIs for CRUD (Create, Read, Update, Delete)
 * operations on the Mata Kuliah entity.</p>
 */
@RestController
@RequestMapping("/api/matakuliah")
public class MataKuliahController {
    
    @Autowired
    private MataKuliahService mataKuliahService;

    /**
     * Adds a new Mata Kuliah record.
     *
     * @param mataKuliah the MataKuliah object to be created
     * @return ResponseEntity containing the created MataKuliah
     */
    @PostMapping
    public ResponseEntity<MataKuliah> createMataKuliah(@RequestBody MataKuliah mataKuliah) {
        return ResponseEntity.ok(mataKuliahService.createMataKuliah(mataKuliah));
    }

    /**
     * Retrieves all Mata Kuliah records.
     *
     * @return ResponseEntity containing a list of all MataKuliah
     */
    @GetMapping
    public ResponseEntity<List<MataKuliah>> getAllMataKuliah() {
        List<MataKuliah> mataKuliahList = mataKuliahService.getAllMataKuliah();
        System.out.println("Mata Kuliah Data: " + mataKuliahList); // Debugging
        return ResponseEntity.ok(mataKuliahList);
    }

    /**
     * Retrieves Mata Kuliah data by ID.
     *
     * @param id the ID of the Mata Kuliah to be retrieved
     * @return ResponseEntity containing the MataKuliah with the specified ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<MataKuliah> getById(@PathVariable Long id) {
        MataKuliah mataKuliahWithDetails = mataKuliahService.getMataKuliahWithDetails(id);
        return ResponseEntity.ok(mataKuliahWithDetails);
    }

    /**
     * Updates Mata Kuliah data by ID.
     *
     * @param id the ID of the Mata Kuliah to be updated
     * @param mataKuliah the new MataKuliah data to replace the old data
     * @return ResponseEntity containing the updated MataKuliah
     */
    @PutMapping("/{id}")
    public ResponseEntity<MataKuliah> updateMataKuliah(@PathVariable Long id, @RequestBody MataKuliah mataKuliah) {
        return ResponseEntity.ok(mataKuliahService.updateMataKuliah(id, mataKuliah));
    }

    /**
     * Deletes Mata Kuliah data by ID.
     *
     * @param id the ID of the Mata Kuliah to be deleted
     * @return ResponseEntity with no content (204 No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMataKuliah(@PathVariable Long id) {
        mataKuliahService.deleteMataKuliah(id);
        return ResponseEntity.noContent().build();
    }
}