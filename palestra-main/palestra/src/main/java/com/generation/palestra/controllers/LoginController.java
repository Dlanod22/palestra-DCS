package com.generation.palestra.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.services.AppService;
import com.generation.palestra.services.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;

    @Autowired
    private AppService appService;

    @GetMapping("/loginpage")
    public String loginPage(Model model){
        if(appService.getMessage() != null)
        {
            model.addAttribute("message", appService.getMessage());
            appService.setMessage(null);
        }
        return "loginpage.html";
    }

    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> params, HttpSession session){
        Persona p = loginService.login(params.get("username"), params.get("password"));
        if(p != null){
            if(p instanceof Cliente)
            {
                session.setAttribute("role", "STUD");
                session.setAttribute("persona", p);
                return "redirect:/area-cliente";
            }
            else if(p instanceof Istruttore)
            {
                session.setAttribute("role", "DOC");
                session.setAttribute("persona", p);
                return "redirect:/area-istruttore";
            }
            else if(p instanceof Manager)
            {
                session.setAttribute("role", "DIR");
                session.setAttribute("persona", p);
                return "redirect:/area-manager";
            }
        }
        appService.setMessage("Errore credenziali errate");
        return "redirect:/loginpage";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/loginpage";
    }


}
