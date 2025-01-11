package com.ohadr.oauth.resource_server.mvc;

import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController
{

//    @RequestMapping(method = RequestMethod.GET)
//    @ResponseBody
//    public String printWelcome() {
//
/// /        model.addAttribute("message", "Spring Security Hello World");
//        return "hello from resource server";
//
//    }

    @GetMapping("/messages")
    public Message[] messages() {
        System.out.println("************************************");
        return new Message[]{new Message("ohad is the king!!!")};
    }

    public record Message(String message) {
    }

}
