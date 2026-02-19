package com.codewithmosh.store.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .sessionManagement(c-> c.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(c-> c
                        .requestMatchers("/carts/**").permitAll() // Allow unauthenticated access to cart endpoints
                        .requestMatchers(HttpMethod.POST, "/users").permitAll() // Allow unauthenticated access to user registration endpoint for post requests
                        .anyRequest().authenticated() // Require authentication for all other endpoints. This line has to be after the cart matcher, otherwise it will override it and require authentication for cart endpoints as well.
                );

        return http.build();
    }
}
