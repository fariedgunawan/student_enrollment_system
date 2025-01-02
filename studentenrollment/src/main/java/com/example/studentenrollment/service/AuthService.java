package com.example.studentenrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.studentenrollment.model.User;
import com.example.studentenrollment.repository.UserRepository;

/**
 * Service class responsible for handling authentication-related operations.
 * 
 * <p>This class provides the necessary methods to authenticate a user based on 
 * their username and password. It communicates with the {@link UserRepository} 
 * to fetch user details and verify credentials.</p>
 * 
 * <p>The authentication logic is implemented in the {@link #authenticate(String, String)} 
 * method, which returns a {@link User} entity if authentication is successful, 
 * or {@code null} if the credentials are invalid.</p>
 */
@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Authenticates a user by comparing the provided username and password with 
     * the stored credentials in the database.
     * 
     * <p>This method retrieves the user by their username and compares the provided 
     * password with the stored password. If the credentials match, the user is returned, 
     * otherwise {@code null} is returned.</p>
     * 
     * @param username The username of the user trying to authenticate.
     * @param password The password of the user trying to authenticate.
     * @return A {@link User} object if the authentication is successful, 
     *         or {@code null} if the authentication fails.
     */
    public User authenticate(String username, String password) {
        // Search user by name
        User user = userRepository.findByUsername(username).orElse(null);

        // Password verification
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }

        // Return null if authentication fails
        return null;
    }
}