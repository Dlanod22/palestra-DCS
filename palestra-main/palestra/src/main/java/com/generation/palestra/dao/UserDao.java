package com.generation.palestra.dao;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.database.Database;
import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Persona;
import com.generation.palestra.entities.Cliente;
//import com.generation.palestra.services.ManagerService;
//import com.generation.palestra.services.IstruttoreService;

@Service
public class UserDao 
{
    
    @Autowired
    private Database database;

    private final String readByUsernameAndPassword = "select id from persone where username=? and password=?";

    private final String updateUsernameAndPassword = "update persone set username=?, password=? where id=?";

    public Long readByUsernameAndPassword(String username, String password)
    {
        Map<Long, Map<String, String>> result = database.executeQuery(readByUsernameAndPassword, username, password);
        Long id = -1L;

        for(Entry<Long, Map<String, String>> coppia : result.entrySet())
        {
            id = coppia.getKey();
        }

        return id;
    }

    //DML che permette l'aggiornamento delle colonne username e password sulla tabella persone
    public void updateUsernameAndPassword(Long idPersona, String username, String password)
    {
        database.executeUpdate(updateUsernameAndPassword, username, password, String.valueOf(idPersona));
    }

}
