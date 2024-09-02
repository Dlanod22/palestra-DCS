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
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.palestra.entities.Scheda;
import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.entities.PianoAbbonamento;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.services.SchedaService;
import com.generation.palestra.services.IstruttoreService;
import com.generation.palestra.services.AppService;
import com.generation.palestra.services.ClienteService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SchedaController {
    
    @Autowired
    private IstruttoreService istruttoreService;

    @Autowired
    private SchedaService schedaService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ApplicationContext context;


    //http://localhost:8080/updateScheda?id=[VALORE]
    @PostMapping("/updateScheda")
    public String updateScheda(@RequestParam(name = "id", defaultValue = "0") Long idCliente, @RequestParam Map<String, String> params, HttpSession session, Model model)
    {
        Persona p = (Persona) session.getAttribute("persona");
        String role = (String) session.getAttribute("role");
        AppService as = context.getBean(AppService.class);
        
        System.out.println("ID Cliente: " + idCliente);  // Debug dell'ID cliente
        System.out.println("Role: " + role);  // Debug del ruolo
        System.out.println("Persona: " + p);  // Debug della persona
        
        if (role != null && role.equals("DOC") && p instanceof Istruttore) 
        {
            Cliente s = clienteService.readById(idCliente);
            System.out.println("Cliente: " + s);  // Debug del cliente
            
            if (s == null) {
                as.setMessage("Errore: Cliente non trovato.");
                return "redirect:/area-istruttore";
            }
            
            Scheda c = schedaService.readById(s.getScheda().getId());
            System.out.println("Scheda: " + c);  // Debug della scheda
    
            if (c == null) {
                as.setMessage("Errore: Scheda non trovata.");
                return "redirect:/area-istruttore";
            }
    
            c.setEse1(params.get("ese1"));
        System.out.println("Ese1 impostato a: " + params.get("ese1"));

        c.setEse2(params.get("ese2"));
        System.out.println("Ese2 impostato a: " + params.get("ese2"));

        c.setEse3(params.get("ese3"));
        System.out.println("Ese3 impostato a: " + params.get("ese3"));

        c.setEse4(params.get("ese4"));
        System.out.println("Ese4 impostato a: " + params.get("ese4"));

        c.setEse5(params.get("ese5"));
        System.out.println("Ese5 impostato a: " + params.get("ese5"));

        c.setEse6(params.get("ese6"));
        System.out.println("Ese6 impostato a: " + params.get("ese6"));

        c.setEse7(params.get("ese7"));
        System.out.println("Ese7 impostato a: " + params.get("ese7"));

        c.setEse8(params.get("ese8"));
        System.out.println("Ese8 impostato a: " + params.get("ese8"));

        c.setEse9(params.get("ese9"));
        System.out.println("Ese9 impostato a: " + params.get("ese9"));

        c.setId_cliente(idCliente);
        System.out.println("Id_cliente impostato a: " + idCliente);

            
            schedaService.update(c);
            s.setScheda(c);
            
            clienteService.update(s);
            as.setMessage("Modifica avvenuta con successo");
            return "redirect:/area-istruttore";
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    @GetMapping("/consigliata/{clienteId}")
    public String schedaConsigliata(@PathVariable Long clienteId, Model model) 
    {
        // Recupera il cliente dal database
        Cliente cliente = clienteService.readById(clienteId);
    
        // Ottieni la scheda consigliata
        Scheda consigliata = schedaService.getSchedaConsigliata(cliente);
        System.out.println("\n" + consigliata );

        if (consigliata == null) {
            consigliata = new Scheda(); // Inizializza con un oggetto vuoto o con valori di default
        }

        // Aggiungi i dati al modello
        model.addAttribute("persona", cliente);
        model.addAttribute("scheda", consigliata);
    
        // Restituisce il nome della vista HTML
        return "areaCliente.html";
    }

}