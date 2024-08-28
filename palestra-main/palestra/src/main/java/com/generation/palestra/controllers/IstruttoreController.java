package com.generation.palestra.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.entities.Manager;
import com.generation.palestra.services.AppService;
import com.generation.palestra.services.IstruttoreService;
import com.generation.palestra.services.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/istruttore")
public class IstruttoreController 
{

    @Autowired
    private IstruttoreService istruttoreService;

    @Autowired
    private ApplicationContext context;
    
    // Questo controller si occupa di tutte le operazioni sugli Istruttori, siano esse performate da un Dirigente, Docente oppure Istruttore
    // Ogni metodo endpoint ha dentro un controllo su HttpSession che contiene il login effettuato, in base all'operazione da effettuare 
    // controlliamo il ruolo di chi ha effettuato la richiesta (esempio solo il dirigente può eliminare, modificare, inserire o leggere tutti gli istruttori)

    // http://localhost:8080/istruttore/insert
    @PostMapping("/insert")
    public String insertIstruttore(@RequestParam Map<String, String> params, HttpSession session){
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);

        if(role != null && role.equals("MAN") && p instanceof Manager){
            Istruttore i = context.getBean(Istruttore.class, params);
            istruttoreService.create(i);
            as.setMessage("Inserimento Avvenuto con successo");
            return "redirect:/area-manager";
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    // Questo endpoint in realtà racchiude in sé infiniti endpoint, grazie all'uso della variabile di percorso
    // http://localhost:8080/istruttore/delete/[NUMERO]
    @GetMapping("/delete/{idIstruttore}")
    public String delete(@PathVariable("idIstruttore") Long idIstruttore, HttpSession session)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);

        if(role != null && role.equals("MAN") && p instanceof Manager)
        {
            // Controlliamo che l'id dell'Istruttore da eliminare sia valido
            if(idIstruttore <= 0){
                as.setMessage("Errore id Istruttore non valido");
                return "redirect:/area-manager";
            }
            else{
                istruttoreService.delete(idIstruttore);
                as.setMessage("Eliminazione avvenuta con successo");
                return "redirect:/area-manager";
            }
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    @PostMapping("/update")
    public String update(@RequestParam Map<String, String> params, HttpSession session){
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);
        
        if(role != null && role.equals("MAN") && p instanceof Manager){
            // Creo l'Istruttore sulla base dei parametri che mi arrivano da frontend
            Istruttore i = context.getBean(Istruttore.class, params);
            istruttoreService.update(i);
            as.setMessage("Modifica avvenuta con successo");
            return "redirect:/area-manager";
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    @GetMapping("/createUser/{idIstruttore}")
    public String createUser(@PathVariable("idIstruttore") Long idIstruttore, HttpSession session)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);

        if(role != null && role.equals("MAN") && p instanceof Manager){
            // Creo un'utenza standard per l'Istruttore in questione (dall'id che mi arriva) usando il servizio .createOrUpdateUser()
            Istruttore i = istruttoreService.readById(idIstruttore);
            String username = (i.getNome() + "." + i.getCognome()).toLowerCase() + String.valueOf(i.getId());
            String password = "1234";
            istruttoreService.createOrUpdateUser(idIstruttore, username, password);
            return "redirect:/area-manager";
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam Map<String, String> params, HttpSession session){
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);
        LoginService loginService = context.getBean(LoginService.class);

        // Questo endpoint si occupa di cambiare la password all'utente che lo ha richiesto, viene effettuato un doppio controllo
        // 1) Se è presente nella session un login da Istruttore
        // 2) se chi ha richiesto il cambio password è stato in grado di fornire le credenziali corrette della stessa persona che ha effettuato il login
        // se queste condizioni sono verificate eseguo la modifica della password

        if(role != null && role.equals("ISTR") && p instanceof Istruttore){
            String username = params.get("username");
            String oldPassword = params.get("old-password");
            String newPassword = params.get("new-password");
            Long id = Long.parseLong(params.get("id"));

            Persona checkPerson = loginService.login(username, oldPassword);
            if(checkPerson != null && checkPerson.getId() == p.getId()){
                istruttoreService.createOrUpdateUser(id, username, newPassword);
                as.setMessage("Password modificata correttamente");
                return "redirect:/area-istruttore";
            }
        }
        as.setMessage("Errore login non effettuato oppure credenziali errate");
        session.invalidate();
        return "redirect:/loginpage";
    }
}

