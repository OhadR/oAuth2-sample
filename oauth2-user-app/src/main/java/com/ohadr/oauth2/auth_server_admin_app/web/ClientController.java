package com.ohadr.oauth2.auth_server_admin_app.web;

import java.util.UUID;
import java.time.Duration;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.log4j.Logger;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/clients")
public class ClientController {

    private final RegisteredClientRepository clientRepository;

    public ClientController(RegisteredClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping
    public ResponseEntity<?> registerClient(@RequestBody ClientRegistrationRequest request) {
        log.debug("********** registerClient() ***********");
        String generatedString = RandomStringUtils.randomAlphanumeric(10);

        String clientSecret = UUID.randomUUID().toString();

        RegisteredClient client = RegisteredClient.withId(clientSecret)
                .clientId(generatedString)
                .clientSecret(new BCryptPasswordEncoder().encode(clientSecret))
                .clientName(request.getClientName())
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                .redirectUri(request.getRedirectUri())
                .scope("read")
                .scope("write")
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                .tokenSettings(TokenSettings.builder().accessTokenTimeToLive(Duration.ofHours(1)).build())
                .build();

        clientRepository.save(client);
        return ResponseEntity.ok(client);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<?> getClient(@PathVariable String clientId) {
        log.info("***************************");
        RegisteredClient client = clientRepository.findByClientId(clientId);
        log.debug("getClient(): " + client);
        return client != null ? ResponseEntity.ok(client) : ResponseEntity.notFound().build();
    }
}
