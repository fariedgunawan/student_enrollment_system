package com.example.studentenrollment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

/**
 * Abstract class representing a generic user in the system.
 * 
 * <p>This class serves as the base class for all user types in the system, such as 
 * students (Mahasiswa) and administrative staff (LAA). It provides common properties 
 * and functionalities for all user roles.</p>
 * 
 * <p>Using the {@code @Inheritance} annotation with the {@code InheritanceType.JOINED} 
 * strategy allows the system to store child classes in separate tables while maintaining 
 * a shared primary key across all user types.</p>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    /**
     * The unique identifier for the user.
     * 
     * <p>This ID is automatically generated using the {@code GenerationType.IDENTITY} strategy.</p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The username for the user.
     * 
     * <p>This is used for user authentication and must be unique.</p>
     */
    private String username;

    /**
     * The password for the user.
     * 
     * <p>This is used for user authentication and is typically stored as a hashed value.</p>
     */
    private String password;

    /**
     * The role of the user in the system.
     * 
     * <p>This is stored as a string representation of the {@link Role} enumeration.</p>
     */
    @Enumerated(EnumType.STRING)
    private Role role;

    /**
     * Default constructor for the {@code User} class.
     * 
     * <p>This is required for JPA and other frameworks that instantiate entity objects.</p>
     */
    public User() {}

    /**
     * Constructor to create a {@code User} with specific attributes.
     * 
     * @param username The username of the user.
     * @param password The password of the user.
     * @param role The role of the user in the system.
     */
    public User(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    /**
     * Gets the unique identifier of the user.
     * 
     * @return The ID of the user.
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets the username of the user.
     * 
     * @return The username of the user.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the user.
     * 
     * @param username The new username for the user.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the password of the user.
     * 
     * <p>Note: In a real-world application, avoid exposing raw passwords and 
     * implement proper security practices.</p>
     * 
     * @return The password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * 
     * <p>Ensure the password is securely hashed before setting it.</p>
     * 
     * @param password The new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the role of the user.
     * 
     * @return The role of the user as defined in the {@link Role} enumeration.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the role of the user.
     * 
     * @param role The new role for the user.
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
