package com.generation.palestra.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController 
{
    
    @GetMapping("/index")
    public String root()
    {
        return "index.html";
    }

    @GetMapping("/registerpage")
    public String registrazione()
    {
        return "registrati.html";
    }

    @GetMapping("/chisiamo")
    public String chisiamo()
    {
        return "chiSiamo.html";
    }

    @GetMapping("/recensioni")
    public String recensione()
    {
        return "recensione.html";
    }

    @GetMapping("/abbonamenti")
    public String abbonamenti()
    {
        return "abbonamenti.html";
    }

    @GetMapping("/corsi")
    public String corsi()
    {
        return "corsi.html";
    }

    @GetMapping("/privacy")
    public String privacy()
    {
        return "privacy.html";
    }
    
    @GetMapping("/terminiecondizioni")
    public String terminiecondizioni()
    {
        return "terminiecondizioni.html";
    }

}