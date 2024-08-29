package com.generation.palestra.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.palestra.entities.Cliente;
import com.generation.palestra.entities.Corso;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.services.AppService;
import com.generation.palestra.services.ClienteService;
import com.generation.palestra.services.CorsoService;
import com.generation.palestra.services.IstruttoreService;
import com.generation.palestra.services.LoginService;

import jakarta.servlet.http.HttpSession;

    @Controller
    public class CorsoController 
    {
        
        @Autowired
        private IstruttoreService istruttoreService;
    
        @Autowired
        private ClienteService clienteService;
    
        @Autowired
        private CorsoService corsoService;
    
        //http://localhost:8080/classe?id=[VALORE]
        @GetMapping("/corso")
        public String dettaglioCorso(@RequestParam(name = "id", defaultValue = "0") Long idCorso, HttpSession session, Model model){
            Persona p = (Persona)session.getAttribute("persona");
            String role = (String)session.getAttribute("role");
            if(role != null && (role.equals("DIR") && p instanceof Manager ||
                                role.equals("DOC") && p instanceof Istruttore)
              )
            {
    
                List<Istruttore> listaIstruttori = istruttoreService.readByIdCorso(idCorso);
                List<Cliente> listaClienti = clienteService.readByIdPiano(idCorso);
                Corso c = corsoService.readById(idCorso);
        
                model.addAttribute("listaIstruttori", listaIstruttori);
                model.addAttribute("listaClienti", listaClienti);
                model.addAttribute("corso", c);
        
                return "dettaglioCorso.html";
    
            }
    
    
            return "redirect:/loginpage";
        }
    
    }
