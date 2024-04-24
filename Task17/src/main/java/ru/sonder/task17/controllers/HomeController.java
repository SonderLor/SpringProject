package ru.sonder.task17.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@RequestMapping("/home")
@Controller
public class HomeController {

    @GetMapping
    public String home() throws IOException {
        return "home";
    }
}
