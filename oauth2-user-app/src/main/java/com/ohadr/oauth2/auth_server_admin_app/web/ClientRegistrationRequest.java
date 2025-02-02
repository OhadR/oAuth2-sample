package com.ohadr.oauth2.auth_server_admin_app.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Getter
@Setter
@NoArgsConstructor // <--- THIS is it
public class ClientRegistrationRequest {
    private String clientId;
    private String clientSecret;
    private String clientName;
    private String redirectUri;

    // Constructors
    public ClientRegistrationRequest(String clientId, String clientSecret, String redirectUri) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }
}
