package com.ohadr.oauth2.auth_server_admin_app.web;

import com.ohadr.oauth2.auth_server_admin_app.services.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody UserCreationRequest request) {
        log.debug("********** createAccount() ***********");

        try {
            this.userService.createUser(request.getUsername(), request.getPassword());
            return ResponseEntity.ok("User created successfully");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }

    }
}
