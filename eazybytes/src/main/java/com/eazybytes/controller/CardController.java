package com.eazybytes.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

     @GetMapping("/card")
    public String welcome(){
        return "Here is my Card.";
    }

}
