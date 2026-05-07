package com.hbtech.cheptel.config;

import com.hbtech.cheptel.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (WebSecurity web) -> web.ignoring().requestMatchers(
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/api-docs",
                "/api-docs/**",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/api/v1/swagger-ui.html",
                "/api/v1/swagger-ui/**",
                "/api/v1/api-docs",
                "/api/v1/api-docs/**",
                "/api/v1/v3/api-docs",
                "/api/v1/v3/api-docs/**"
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                .authorizeHttpRequests(auth -> auth

                        // ========== PUBLICS ==========
                        .requestMatchers(
                                "/auth/**",
                                "/docs",
                                "/swagger",
                                "/swagger-ui",
                                "/api-docs",
                                "/api-docs/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/webjars/**",
                                "/files/photos/**",
                                "/files/audio/**"
                        ).permitAll()

                        // ========== FICHIERS ==========
                        .requestMatchers("/files/upload/**")
                        .authenticated()

                        // ========== ANIMAUX ==========
                        .requestMatchers("/animals/**")
                        .hasAnyRole("FERMIER", "VETERINAIRE", "CONTROLEUR", "ADMIN")

                        // ========== FERMIER ==========
                        .requestMatchers("/fermier/**")
                        .hasAnyRole("FERMIER", "ADMIN")

                        // ========== VETERINAIRE ==========
                        .requestMatchers("/veterinaire/**")
                        .hasAnyRole("VETERINAIRE", "ADMIN")

                        // ========== FERMES ==========
                        .requestMatchers("/farms/**")
                        .hasAnyRole("VETERINAIRE", "CONTROLEUR", "ADMIN")

                        // ========== CONTROLEUR ==========
                        .requestMatchers("/controleur/**")
                        .hasAnyRole("CONTROLEUR", "ADMIN")

                        // ========== CONSTATS ==========
                        .requestMatchers("/constats/**")
                        .hasAnyRole("CONTROLEUR", "ADMIN")

                        // ========== MOUVEMENTS ==========
                        .requestMatchers("/movements/**")
                        .hasAnyRole("FERMIER", "VETERINAIRE", "CONTROLEUR", "ADMIN")

                        // ========== REPRODUCTIONS ==========
                        .requestMatchers("/reproductions/**")
                        .hasAnyRole("VETERINAIRE", "FERMIER", "ADMIN")

                        // ========== ALERTES ==========
                        .requestMatchers("/alerts/**")
                        .hasAnyRole("VETERINAIRE", "FERMIER", "ADMIN")

                        // ========== SYNCHRONISATION ==========
                        .requestMatchers("/sync/**")
                        .hasAnyRole("FERMIER", "VETERINAIRE", "CONTROLEUR", "ADMIN")

                        // ========== TOUT LE RESTE ==========
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(
                Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH")
        );
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(false);
        configuration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
