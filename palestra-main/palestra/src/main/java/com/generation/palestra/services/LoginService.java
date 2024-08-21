package com.generation.palestra.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.UserDao;
import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.entities.Cliente;

@Service
public class LoginService 
{
    @Autowired
    private UserDao userDAO;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private IstruttoreService istruttoreService;

    @Autowired
    private ManagerService managerService;

    //TODO: DocenteService

    public Persona login(String username, String password)
    {
        Long id = userDAO.readByUsernameAndPassword(username, password);
        if(id > 0)
        {
            Cliente c = clienteService.readById(id);
            if( c != null)
            {
                return c;
            }

            Istruttore istr = istruttoreService.readById(id);
            if(istr != null){
                return istr;
            }

            Manager dir = managerService.readById(id);
            if(dir != null){
                return dir;
            }
            
        }

        return null;
    }
}
