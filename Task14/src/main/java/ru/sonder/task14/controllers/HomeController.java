package ru.sonder.task14.controllers;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
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
