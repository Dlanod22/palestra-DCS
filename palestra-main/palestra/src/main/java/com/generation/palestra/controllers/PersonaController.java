package com.generation.palestra.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.services.AppService;
import com.generation.palestra.services.CorsoService;
import com.generation.palestra.services.ClienteService;
import com.generation.palestra.services.PianoAbbonamentoService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PersonaController {

    @Autowired
    private ClienteService clienteService;

    
    @Autowired
    private CorsoService corsoService;

    @Autowired
    private PianoAbbonamentoService pianoAbbonamentoService;

    @Autowired
    private ApplicationContext context;

    @GetMapping("/area-cliente")
    public String areaCliente(HttpSession session, Model model)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");

        if(role != null && role.equals("STUD") && p instanceof Cliente)
        {
            model.addAttribute("persona", (Cliente)p);

            AppService as = context.getBean(AppService.class);
            if(as.getMessage() != null){
                model.addAttribute("message", as.getMessage());
                as.setMessage(null);
            }

            return "areaCliente.html";
        }
        session.invalidate();
        return "redirect:/loginpage";
    }

    @GetMapping("/area-istruttore")
    public String areaIstruttore(HttpSession session, Model model)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");

        if(role != null && role.equals("DOC") && p instanceof Istruttore)
        {
            model.addAttribute("persona", (Istruttore)p);

            AppService as = context.getBean(AppService.class);
            if(as.getMessage() != null){
                model.addAttribute("message", as.getMessage());
                as.setMessage(null);
            }

            return "areaIstruttore.html";
        }
        session.invalidate();
        return "redirect:/loginpage";
    }

    @GetMapping("/area-manager")
    public String areaManager(HttpSession session, Model model)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");

        if(role != null && role.equals("DIR") && p instanceof Manager)
        {
            model.addAttribute("persona", (Manager)p);
            model.addAttribute("listaClienti", clienteService.readAll());
            model.addAttribute("listaPiani", pianoAbbonamentoService.readAll());
            
            AppService as = context.getBean(AppService.class);
            if(as.getMessage() != null)
            {
                model.addAttribute("message", as.getMessage());
                as.setMessage(null);
            }
            
            return "areaManager.html";
        }
        session.invalidate();
        return "redirect:/loginpage";
    }


    //Qeusto endpoint si occupa di eseguire e applicare i filtri della ricerca sulla lettura degli studenti, da frontend ci arriveranno
    //i parametri di ricerca "nome" e "classe" dai quali estrapoliamo i valori e eseguiamo tramite il service .readByFilters() la query a DB
    @GetMapping("/area-manager-search")
    public String areaManager(
                                @RequestParam(name = "nome", defaultValue = "") String nome, 
                                @RequestParam(name = "piano", defaultValue = "0") Long idPiano,
                                HttpSession session, Model model
                             )           
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        
        if(role != null && role.equals("DIR") && p instanceof Manager){
            model.addAttribute("persona", (Manager)p);
            model.addAttribute("listaClienti", clienteService.readByFilters(nome, idPiano));
            model.addAttribute("listaPiani", pianoAbbonamentoService.readAll());
            
            AppService as = context.getBean(AppService.class);
            if(as.getMessage() != null){
                model.addAttribute("message", as.getMessage());
                as.setMessage(null);
            }
            
            return "areaManager.html";
        }
        session.invalidate();
        return "redirect:/loginpage";

    }
}
