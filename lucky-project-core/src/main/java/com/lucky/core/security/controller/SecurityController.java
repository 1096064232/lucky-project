package com.lucky.core.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

    @GetMapping("/me")
    public Object getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
