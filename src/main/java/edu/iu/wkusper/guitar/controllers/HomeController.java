package edu.iu.wkusper.guitar.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class HomeController {

    @GetMapping("/")
    public String greeting() {
        return "Welcome to the Guitar Inventory System!";
    }
}
