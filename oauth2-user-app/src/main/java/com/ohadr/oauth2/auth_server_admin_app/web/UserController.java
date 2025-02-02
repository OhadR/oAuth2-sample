package com.ohadr.oauth2.auth_server_admin_app.web;

import com.ohadr.oauth2.auth_server_admin_app.services.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<?> createClient() {
        log.info("***************************");
        this.userService.createUser("admin", "password", "ADMIN");
        return (ResponseEntity<?>) ResponseEntity.ok();
    }
}
