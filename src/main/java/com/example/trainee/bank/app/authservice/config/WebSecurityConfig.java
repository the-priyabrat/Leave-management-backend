package com.example.trainee.bank.app.authservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Order(1)
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    public static final String ADMIN = "admin";
    public static final String USER = "user";

    public static String getAdmin() {
        return ADMIN;
    }

    public static String getUser() {
        return USER;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                .requestMatchers(HttpMethod.POST, "/token/get").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/welcome").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/check").permitAll()
                .requestMatchers(HttpMethod.POST, "/api/save").permitAll()
                .anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());
//        http.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        http.oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//		http.oauth2ResourceServer().jwt();
        return http.build();
    }
}