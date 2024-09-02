package com.generation.palestra.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController 
{
    
    @GetMapping("/")
    public String root()
    {
        return "index.html";
    }

    @GetMapping("/registerpage")
    public String registrazione()
    {
        return "registrati.html";
    }

}