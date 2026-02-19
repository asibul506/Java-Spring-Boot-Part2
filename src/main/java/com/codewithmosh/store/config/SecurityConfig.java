package com.codewithmosh.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // This bean is used to encode passwords before storing them in the database. It uses the BCrypt hashing algorithm, which is a strong and secure way to hash passwords.
    // By defining this bean, we can inject it into our services or controllers where we need to encode passwords, ensuring that user passwords are stored securely.
    // Additionally, Spring Security requires a PasswordEncoder bean to be defined in order to handle password encoding and matching during authentication.
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This bean configures the security filter chain for the application. It defines how HTTP requests are secured and what kind of authentication is required for different endpoints.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement(c-> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(c-> c
                        .requestMatchers("/carts/**").permitAll() // Allow unauthenticated access to cart endpoints
                        .requestMatchers("/auth/login").permitAll() // Allow unauthenticated access to authentication endpoints
                        .requestMatchers(HttpMethod.POST, "/users").permitAll() // Allow unauthenticated access to user registration endpoint for post requests
                        .anyRequest().authenticated() // Require authentication for all other endpoints. This line has to be after the cart matcher, otherwise it will override it and require authentication for cart endpoints as well.
                );

        return http.build();
    }
}
