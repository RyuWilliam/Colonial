package com.colonial.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivamos CSRF solo para pruebas con Postman o front local
                .csrf(csrf -> csrf.disable())

                // Configuración de rutas
                .authorizeHttpRequests(auth -> auth
                        // Permitimos el acceso sin autenticación a ciertos endpoints (por ejemplo login)
                        .requestMatchers("/api/users/save", "/login", "/h2-console/**", "/swagger-ui/**").permitAll()

                        // Permitimos temporalmente todos los métodos (GET, POST, PUT, DELETE)
                        .anyRequest().permitAll()
                )

                // Usamos autenticación básica temporalmente (útil para probar)
                .httpBasic(httpBasic -> {});

        return http.build();
    }
}
