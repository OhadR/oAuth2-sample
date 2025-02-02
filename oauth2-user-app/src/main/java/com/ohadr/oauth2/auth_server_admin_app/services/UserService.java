package com.ohadr.oauth2.auth_server_admin_app.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class UserService {
    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    public UserService(DataSource dataSource, PasswordEncoder passwordEncoder) {
        this.userDetailsManager = new JdbcUserDetailsManager(dataSource);
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(String username, String rawPassword, String... roles) {
        String encodedPassword = passwordEncoder.encode(rawPassword);
        UserDetails user = User.withUsername(username)
                .password(encodedPassword)
                .roles(roles) // Roles are prefixed with "ROLE_"
                .build();
        userDetailsManager.createUser(user);
    }
}
