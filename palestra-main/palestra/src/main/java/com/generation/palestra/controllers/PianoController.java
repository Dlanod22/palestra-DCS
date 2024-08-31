package com.generation.palestra.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.palestra.entities.PianoAbbonamento;
import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.services.PianoAbbonamentoService;
import com.generation.palestra.services.IstruttoreService;
import com.generation.palestra.services.ClienteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PianoController {
    
    @Autowired
    private IstruttoreService istruttoreService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PianoAbbonamentoService pianoAbbonamentoService;

    //http://localhost:8080/PianoAbbonamento?id=[VALORE]
    @GetMapping("/pianoAbbonamento")
    public String dettaglioPianoAbbonamento(@RequestParam(name = "id", defaultValue = "0") Long idPianoAbbonamento, HttpSession session, Model model){
        
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        
        if(role != null && (role.equals("DIR") && p instanceof Manager ||
                            role.equals("DOC") && p instanceof Istruttore)
        )
        {

            List<Cliente> listaClienti = clienteService.readByIdPiano(idPianoAbbonamento);
            PianoAbbonamento c = pianoAbbonamentoService.readById(idPianoAbbonamento);
    
            model.addAttribute("listaClienti", listaClienti);
            model.addAttribute("pianoAbbonamento", c);
    
            return "dettaglioPiano.html";

        }


        return "redirect:/loginpage";
    }

}

