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

import com.example.studentenrollment.model.Laa;
import com.example.studentenrollment.service.LaaService;

/**
 * LaaController is a REST controller that handles CRUD operations for the Laa entity.
 * It provides endpoints for creating, reading, updating, and deleting Laa records.
 */
@RestController
@RequestMapping("/api/laa")
public class LaaController {

    @Autowired
    private LaaService laaService;

    /**
     * Creates a new Laa record.
     * 
     * @param laa the Laa object to be created.
     * @return a ResponseEntity containing the created Laa object.
     */
    @PostMapping
    public ResponseEntity<Laa> createLaa(@RequestBody Laa laa) {
        return ResponseEntity.ok(laaService.createLaa(laa));
    }

    /**
     * Retrieves all Laa records.
     * 
     * @return a ResponseEntity containing a list of all Laa records.
     */
    @GetMapping
    public ResponseEntity<List<Laa>> getAllLaa() {
        return ResponseEntity.ok(laaService.getAllLaa());
    }

    /**
     * Retrieves a Laa record by its ID.
     * 
     * @param id the ID of the Laa record to retrieve.
     * @return a ResponseEntity containing the Laa record with the specified ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Laa> getLaaById(@PathVariable Long id) {
        return ResponseEntity.ok(laaService.getLaaById(id));
    }

    /**
     * Updates an existing Laa record.
     * 
     * @param id the ID of the Laa record to update.
     * @param laa the updated Laa object.
     * @return a ResponseEntity containing the updated Laa record.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Laa> updateLaa(@PathVariable Long id, @RequestBody Laa laa) {
        return ResponseEntity.ok(laaService.updateLaa(id, laa));
    }

    /**
     * Deletes a Laa record by its ID.
     * 
     * @param id the ID of the Laa record to delete.
     * @return a ResponseEntity with no content after the Laa record is deleted.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLaa(@PathVariable Long id) {
        laaService.deleteLaa(id);
        return ResponseEntity.noContent().build();
    }
}
