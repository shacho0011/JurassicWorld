package com.mohit.jmc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/m")
public class HelloRestController {

    @GetMapping("/hello")
    public String getHello(){
        return "Hello World";
    }
}
