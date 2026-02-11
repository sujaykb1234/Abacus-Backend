package com.abacus.franchise.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/abacus/v1/auth/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/franchise/**").hasAnyRole("FRANCHISE", "MASTER_FRANCHISE")
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        .anyRequest().authenticated());
        return http.build();
    }
}
