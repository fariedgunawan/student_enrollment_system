package com.example.studentenrollment.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.studentenrollment.service.CustomUserDetailsService;

/**
 * SecurityConfig is the configuration class for setting up Spring Security in the application.
 * It defines the security rules for the application, including endpoint access control, login/logout behavior,
 * and authentication providers.
 */
@Configuration
public class SecurityConfig {

    /**
     * Custom service to retrieve user details for authentication.
     */
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    /**
     * Configures the security filter chain.
     *
     * @param http the HttpSecurity object to configure security rules
     * @return a configured SecurityFilterChain
     * @throws Exception if any error occurs during configuration
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disables CSRF protection
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/resources/**", "/static/**", "/test-user").permitAll() // Public endpoints
                        .requestMatchers("/dashboard_mahasiswa/**").hasRole("MAHASISWA") // Restricts access to MAHASISWA role
                        .requestMatchers("/dashboard_laa/**").hasRole("LAA") // Restricts access to LAA role
                        .anyRequest().authenticated() // All other requests require authentication
                )
                .formLogin(form -> form
                        .loginPage("/login.html") // Custom login page
                        .loginProcessingUrl("/login") // URL for processing login
                        .defaultSuccessUrl("/dashboard", true) // Redirect after successful login
                        .failureHandler((request, response, exception) -> {
                            System.out.println("Login failed: " + exception.getMessage());
                            response.sendRedirect("/login.html?error"); // Redirect to login page on failure
                        })
                        .permitAll() // Allows access to the login page for all users
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // URL for logging out
                        .logoutSuccessUrl("/login.html") // Redirect after successful logout
                        .permitAll() // Allows access to the logout URL for all users
                );

        return http.build();
    }

    /**
     * Configures the AuthenticationManager bean to manage authentication.
     *
     * @param authConfig the AuthenticationConfiguration object
     * @return the AuthenticationManager bean
     * @throws Exception if an error occurs during configuration
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    /**
     * Configures a DaoAuthenticationProvider bean that uses the CustomUserDetailsService
     * and a PasswordEncoder for authentication.
     *
     * @return the configured DaoAuthenticationProvider
     */
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Configures a PasswordEncoder bean for encoding and verifying passwords.
     * Currently uses NoOpPasswordEncoder (no encoding for demonstration purposes).
     *
     * @return the PasswordEncoder bean
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Configures a UserDetailsService bean that uses the CustomUserDetailsService implementation.
     *
     * @return the UserDetailsService bean
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }
}

