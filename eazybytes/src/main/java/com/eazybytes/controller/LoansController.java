package com.eazybytes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

     @GetMapping("/loans")
    public String welcome(){
        return "Welcome to Spring Security.";
    }

}
