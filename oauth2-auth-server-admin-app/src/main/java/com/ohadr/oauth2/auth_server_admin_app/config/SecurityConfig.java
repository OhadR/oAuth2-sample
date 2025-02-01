package com.ohadr.oauth2.auth_server_admin_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.jdbc.core.*;

import static org.springframework.security.config.Customizer.withDefaults;
import static org.springframework.security.oauth2.core.authorization.OAuth2AuthorizationManagers.hasScope;

@EnableWebSecurity
@Configuration(proxyBeanMethods = false)
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                //The above requires the scope of message:read for any URL that starts with /messages/.
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/messages/**").access(hasScope("message:read"))
                        .anyRequest().authenticated()
                )
                // Form login handles the redirect to the login page from the
                // authorization server filter chain
                .formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcRegisteredClientRepository(jdbcTemplate);
    }
}