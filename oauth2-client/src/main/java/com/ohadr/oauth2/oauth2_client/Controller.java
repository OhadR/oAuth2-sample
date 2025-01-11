package com.ohadr.oauth2.oauth2_client;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    private static Logger log = Logger.getLogger(Controller.class);

    private final RestClient restClient;

    public Controller(RestClient restClient) {
        this.restClient = restClient;
    }

    @GetMapping("/user")
    public Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        log.info("principal: " + principal);
        return Collections.singletonMap("name", principal.getAttribute("sub"));
    }

    @PostMapping("/callResourceServer")
    public ResponseEntity<List<Message>> getMessagesFromResourceServer() {
        System.out.println("calling resource server...");
        log.info("calling resource server...");

        Message[] messages = this.restClient.get()
				.uri("http://localhost:8090/messages")
                .retrieve()
                .body(Message[].class);
        return ResponseEntity.ok(Arrays.asList(messages));

    }

    @GetMapping("/getMessageFromResourceServer")
    public ResponseEntity<List<Message>> getMessageFromResourceServer() {
        log.info("getMessageFromResourceServer: calling resource server...");

        Message[] messages = this.restClient.get()
                .uri("http://localhost:8090/messages")
                .retrieve()
                .body(Message[].class);
        return ResponseEntity.ok(Arrays.asList(messages));

    }

    public record Message(String message) {
    }
}