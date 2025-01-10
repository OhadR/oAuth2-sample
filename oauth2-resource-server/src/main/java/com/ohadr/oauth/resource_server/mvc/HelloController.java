package com.ohadr.oauth.resource_server.mvc;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;

@RestController
public class HelloController
{

//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public String printWelcome() {
//
////        model.addAttribute("message", "Spring Security Hello World");
//        return "hello from resource server";
//
//    }

    @GetMapping("/messages")
    public Map<String, Object> messages() {
        System.out.println("************************************");
        return Collections.singletonMap("name", "login");
    }


}
