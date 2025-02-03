package com.ohadr.oauth2.auth_server_admin_app.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCreationRequest {
    private String username;
    private String password;

    // Constructors
    public UserCreationRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
