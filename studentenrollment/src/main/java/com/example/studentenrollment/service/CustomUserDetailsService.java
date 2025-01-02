package com.example.studentenrollment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.studentenrollment.repository.UserRepository;

/**
 * Custom implementation of {@link UserDetailsService} that loads user details 
 * from the database for authentication and authorization purposes.
 * 
 * <p>This service is used by Spring Security to retrieve user information 
 * (such as username, password, and roles) during authentication. It uses 
 * the {@link UserRepository} to fetch the {@link com.example.studentenrollment.model.User} 
 * entity by username and then constructs a {@link UserDetails} object to represent 
 * the user in the context of Spring Security.</p>
 * 
 * <p>If the user cannot be found by the provided username, a {@link UsernameNotFoundException} 
 * is thrown.</p>
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Loads the user details from the database based on the provided username.
     * 
     * <p>This method fetches the user from the database and converts it into a 
     * {@link UserDetails} object that Spring Security can use. It also assigns roles 
     * to the user based on their {@link com.example.studentenrollment.model.Role}.</p>
     * 
     * @param username The username of the user to load.
     * @return A {@link UserDetails} object representing the authenticated user.
     * @throws UsernameNotFoundException if no user is found with the given username.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.example.studentenrollment.model.User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .build();
    }
}
