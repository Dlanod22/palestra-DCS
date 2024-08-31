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

import com.generation.palestra.entities.PianoAbbonamento;
import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.services.AppService;
import com.generation.palestra.services.PianoAbbonamentoService;
import com.generation.palestra.services.LoginService;
import com.generation.palestra.services.ClienteService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cliente")
public class ClienteController 
{

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PianoAbbonamentoService pianoService;

    @Autowired
    private ApplicationContext context;
    
    //Questo controller si occuopa di tutte le operazioni sugli Studenti, siano esse performate da un Dirigente, Docente oppure Studente
    //Ogno metodo endpoint ha dentro un controllo su HttpSession che contiene il login effettuato, in base all'operazione da effettuare 
    //controlliamo il ruolo di chi ha effettuato la richiesta (esempio solo il dirigente può eliminare, modificare, inserire o leggere tutti gli studenti)



    //http://localhost:8080/studente/insert
    @PostMapping("/insert")
    public String insertCliente(@RequestParam Map<String, String> params, HttpSession session)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);

        if(role != null && role.equals("DIR") && p instanceof Manager)
        {
            Cliente s = context.getBean(Cliente.class, params);
            PianoAbbonamento c = pianoService.readById(Long.parseLong(params.get("piano")));
            s.setPianoAbbonamento(c);
            clienteService.create(s);
            as.setMessage("Inserimento Avvenuto con successo");
            return "redirect:/area-manager";
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    //Questo endpoint in realtà racchiude in se infiniti endpoint, grazie all'uso della variabile di percorso
    //http://localhost:8080/Cliente/delete/[NUMERO]
    @GetMapping("/delete/{idCliente}")
    public String delete(@PathVariable("idCliente") Long idCliente, HttpSession session)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);

        if(role != null && role.equals("DIR") && p instanceof Manager)
        {
            //Controlliamo che l'id dello Cliente da eliminare sia valido
            if(idCliente <= 0)
            {
                as.setMessage("Errore id Cliente non valido");
                return "redirect:/area-manager";
            }
            else{
                clienteService.delete(idCliente);
                as.setMessage("Eliminazione avvenuta con successo");
                return "redirect:/area-manager";
            }
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }


    @PostMapping("/update")
    public String update(@RequestParam Map<String, String> params, HttpSession session)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);
        
        if(role != null && role.equals("DIR") && p instanceof Manager)
        {
            //Creo il Cliente sulla base dei parametri che mi arrivano da frontend, cerco il suo piano dall'id
            //setto il piano al Cliente ed eseguo la modifica
            Cliente s = context.getBean(Cliente.class, params);
            PianoAbbonamento c = pianoService.readById(Long.parseLong(params.get("piano")));

            s.setEta(Integer.parseInt(params.get("eta")));
            s.setPeso(Double.parseDouble(params.get("peso")));
            s.setAltezza(Integer.parseInt(params.get("altezza")));
            s.setSesso(params.get("sesso").charAt(0));
            s.setObiettivo(params.get("obiettivo"));
            s.setPianoAbbonamento(c);
            
            clienteService.update(s);
            as.setMessage("Modifica avvenuta con successo");
            return "redirect:/area-manager";
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    @GetMapping("/createUser/{idCliente}")
    public String createUser(@PathVariable("idCliente") Long idCliente, HttpSession session)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);

        if(role != null && role.equals("DIR") && p instanceof Manager){
            //Creo un'utenza standard per lo Cliente in questione (dall'id che mi arriva) usando il servizio .createOrUpdateUser()
            Cliente s = clienteService.readById(idCliente);
            String username = (s.getNome() + "." + s.getCognome()).toLowerCase() + String.valueOf(s.getId());
            String password = "1234";
            clienteService.createOrUpdateUser(idCliente, username, password);
            return "redirect:/area-manager";
        }
        as.setMessage("Errore richiesta non autorizzata");
        return "redirect:/loginpage";
    }

    @PostMapping("/updateUser")
    public String updateUser(@RequestParam Map<String, String> params, HttpSession session)
    {
        Persona p = (Persona)session.getAttribute("persona");
        String role = (String)session.getAttribute("role");
        AppService as = context.getBean(AppService.class);
        LoginService loginService = context.getBean(LoginService.class);

        //Questo endpoint si occupa di cambiare la password all'utente che lo ha richiesto, viene effettuato un doppio controllo
        //1) Se è presente nella session un login da Cliente
        //2) se chi ha richiesto il cambio password è stato in grado di fornire le credenziali corrette della stessa persona che ha effettuato il login
        //se queste condizioni sono verificate eseguo la modifica della password

        if(role != null && role.equals("STUD") && p instanceof Cliente)
        {
            String username = params.get("username");
            String oldPassword = params.get("old-password");
            String newPassword = params.get("new-password");
            Long id = Long.parseLong(params.get("id"));

            Persona checkPerson = loginService.login(username, oldPassword);
            
            if(checkPerson != null && checkPerson.getId() == p.getId())
            {
                clienteService.createOrUpdateUser(id, username, newPassword);
                as.setMessage("Password modificata correttamente");
                return "redirect:/area-cliente";
            }
        }
        as.setMessage("Errore login non effettuato oppure credenziali errate");
        session.invalidate();
        return "redirect:/loginpage";
    }
}
