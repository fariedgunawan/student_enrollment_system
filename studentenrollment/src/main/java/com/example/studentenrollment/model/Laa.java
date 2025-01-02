package com.example.studentenrollment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Represents the LAA (Academic and Administrative Services) entity in the database.
 * 
 * <p>This class extends the {@link User} class to inherit common user properties,
 * while adding specific attributes for the LAA entity such as NIP (Employee Identification Number)
 * and position.</p>
 */
@Entity
public class Laa extends User {

    /**
     * Unique ID for each LAA entity.
     * 
     * <p>This ID is automatically generated using the {@code GenerationType.IDENTITY} strategy.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Employee Identification Number (NIP) for the LAA.
     */
    private String nip;

    /**
     * Position or role of the LAA.
     */
    private String posisi;

    /**
     * Returns the unique ID of the LAA entity.
     * 
     * @return The unique ID of the LAA.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID for the LAA entity.
     * 
     * @param id The unique ID to be assigned to the LAA entity.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Returns the Employee Identification Number (NIP) of the LAA.
     * 
     * @return The NIP of the LAA.
     */
    public String getNip() {
        return nip;
    }

    /**
     * Sets the Employee Identification Number (NIP) for the LAA.
     * 
     * @param nip The new NIP for the LAA entity.
     */
    public void setNip(String nip) {
        this.nip = nip;
    }

    /**
     * Returns the position or role of the LAA.
     * 
     * @return The position or role of the LAA.
     */
    public String getPosisi() {
        return posisi;
    }

    /**
     * Sets the position or role for the LAA.
     * 
     * @param posisi The new position or role for the LAA entity.
     */
    public void setPosisi(String posisi) {
        this.posisi = posisi;
    }
}