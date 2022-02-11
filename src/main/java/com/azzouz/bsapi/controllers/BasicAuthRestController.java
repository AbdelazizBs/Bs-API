package com.azzouz.bsapi.controllers;

import com.azzouz.bsapi.entities.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class BasicAuthRestController {
    @GetMapping(path = "/basicauth")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }
}

