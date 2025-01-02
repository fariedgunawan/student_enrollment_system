package com.example.studentenrollment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentenrollment.model.Laa;
import com.example.studentenrollment.repository.LaaRepository;

/**
 * Service layer for managing {@link Laa} entities.
 * 
 * <p>This service class provides business logic for creating, retrieving, updating, and deleting
 * {@link Laa} records in the database. It interacts with the {@link LaaRepository} for CRUD operations.</p>
 */
@Service
public class LaaService {

    @Autowired
    private LaaRepository laaRepository;

    /**
     * Creates a new {@link Laa} entity.
     * 
     * <p>This method saves the provided {@link Laa} instance into the database.</p>
     * 
     * @param laa The {@link Laa} entity to create.
     * @return The saved {@link Laa} entity.
     */
    public Laa createLaa(Laa laa) {
        return laaRepository.save(laa);
    }

    /**
     * Retrieves all {@link Laa} entities from the database.
     * 
     * @return A list of all {@link Laa} entities.
     */
    public List<Laa> getAllLaa() {
        return laaRepository.findAll();
    }

    /**
     * Retrieves a {@link Laa} entity by its ID.
     * 
     * <p>If no entity is found with the given ID, a {@link RuntimeException} is thrown.</p>
     * 
     * @param id The ID of the {@link Laa} entity to retrieve.
     * @return The {@link Laa} entity with the given ID.
     * @throws RuntimeException if no {@link Laa} entity is found for the provided ID.
     */
    public Laa getLaaById(Long id) {
        return laaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Laa not found for ID: " + id));
    }

    /**
     * Updates an existing {@link Laa} entity.
     * 
     * <p>This method updates the properties of an existing {@link Laa} entity identified by its ID.
     * If the entity is not found, an exception is thrown. The updated data is saved to the database.</p>
     * 
     * @param id The ID of the {@link Laa} entity to update.
     * @param updatedLaa The updated {@link Laa} entity data.
     * @return The updated {@link Laa} entity.
     * @throws RuntimeException if no {@link Laa} entity is found for the provided ID.
     */
    public Laa updateLaa(Long id, Laa updatedLaa) {
        Laa laa = getLaaById(id);
        laa.setUsername(updatedLaa.getUsername());
        laa.setPassword(updatedLaa.getPassword());
        laa.setNip(updatedLaa.getNip());
        laa.setPosisi(updatedLaa.getPosisi());
        return laaRepository.save(laa);
    }

    /**
     * Deletes a {@link Laa} entity by its ID.
     * 
     * <p>This method removes the {@link Laa} entity with the specified ID from the database.</p>
     * 
     * @param id The ID of the {@link Laa} entity to delete.
     */
    public void deleteLaa(Long id) {
        laaRepository.deleteById(id);
    }
}
