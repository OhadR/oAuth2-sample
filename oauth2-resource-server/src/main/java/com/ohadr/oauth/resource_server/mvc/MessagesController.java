package com.ohadr.oauth.resource_server.mvc;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessagesController
{
    Logger log = LogManager.getLogger(getClass());

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
        log.info("***************************");
        return new Message[]{new Message("ohad is the king!!!")};
    }

    public record Message(String message) {
    }

}
