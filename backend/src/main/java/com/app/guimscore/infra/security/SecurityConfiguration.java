package com.app.guimscore.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("http://localhost:3000", "https://guimscore.com.br")); // ← removi espaço extra
                    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
                    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type", "Accept")); // ← evite "*" com allowCredentials
                    configuration.setAllowCredentials(true);
                    configuration.setExposedHeaders(List.of("Authorization")); // se você retorna token no header
                    cors.configurationSource(request -> configuration);
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorization -> {
                    authorization.requestMatchers("/api/auth/**").permitAll();
                    authorization.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll(); // pré-flight
                    authorization.requestMatchers(HttpMethod.POST, "/api/game-server").authenticated();
                    authorization.requestMatchers(HttpMethod.GET, "/api/game-server").authenticated();
                    authorization.requestMatchers(HttpMethod.DELETE, "/api/game-server/**").authenticated();
                    authorization.requestMatchers(HttpMethod.PUT, "/api/game-server/**").authenticated();
                    authorization.requestMatchers(HttpMethod.GET, "/api/game-server/**").authenticated();
                    authorization.requestMatchers(HttpMethod.POST, "/api/data/**").authenticated();
                    authorization.requestMatchers(HttpMethod.PUT, "/api/data/**").authenticated();
                    authorization.requestMatchers(HttpMethod.DELETE, "/api/data/**").authenticated();
                    authorization.requestMatchers(HttpMethod.GET, "/api/data/**").authenticated();
                    authorization.requestMatchers(HttpMethod.POST, "/api/data?game-id=**").authenticated();
                    authorization.requestMatchers(HttpMethod.POST, "/api/players/**").permitAll();
                    authorization.requestMatchers(HttpMethod.GET, "/api/players/**").permitAll();
                    authorization.anyRequest().denyAll();
                })
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
