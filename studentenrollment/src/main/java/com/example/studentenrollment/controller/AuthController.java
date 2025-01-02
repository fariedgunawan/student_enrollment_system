package com.example.studentenrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.studentenrollment.repository.UserRepository;

/**
 * AuthController handles authentication-related operations in the student enrollment application.
 * It includes endpoints for testing user details and redirecting to the appropriate dashboard based on user roles.
 */
@Controller
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Tests if a user exists based on the provided username.
     * 
     * @param username the username of the user to search for.
     * @return a ResponseEntity with a message containing the user's details if found, or a 404 error if not found.
     */
    @GetMapping("/test-user")
    public ResponseEntity<?> testUser(@RequestParam String username) {
        return userRepository.findByUsername(username)
                .map(user -> ResponseEntity.ok("Found: " + user.getUsername() + ", Role: " + user.getRole()))
                .orElse(ResponseEntity.status(404).body("User not found"));
    }

    /**
     * Redirects the user to the appropriate dashboard based on their role.
     * 
     * @param auth the authentication object containing the user's details.
     * @return a redirect URL to the appropriate dashboard based on the user's role.
     */
    @GetMapping("/dashboard")
    public String redirectDashboard(Authentication auth) {
        String role = auth.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_MAHASISWA")) {
            return "redirect:/dashboard_mahasiswa";
        } else if (role.equals("ROLE_LAA")) {
            return "redirect:/dashboard_laa";
        }

        return "redirect:/login.html";
    }
}
