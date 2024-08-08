package com.generation.palestra.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.database.Database;
import com.generation.palestra.entities.Entity;
import com.generation.palestra.entities.Manager;

@Service
public class ManagerDao implements IDAO<Manager>
{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    private final String insertPersona = "insert into persone(nome, cognome, data_nascita) values (?, ?, ?)";
    
    private final String insertManager = "insert into managers(id) values(?)";
    
    private final String readAllManager = "select p.* from managers m join persone p on m.id = p.id";
    
    private final String updatePersona = "update persone = set nome=?, cognome?, data_nascita=? where id=?";
    
    private final String updateManager =  "update managers set id=? where id=?";
    
    private final String deletePersona = "delete from persone where id=?";

    @Override
    public Long create(Manager e) 
    {
        Long idPersona = database.executeUpdate(insertPersona, 
                                e.getNome(), 
                                e.getCognome(), 
                                String.valueOf(e.getDataNascita())
        );
    
        database.executeUpdate(insertManager, String.valueOf(idPersona));

        return idPersona;

    }

    @Override
    public Map<Long, Entity> readAll() 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readAllManager);

        for(Entry<Long, Map<String, String>> coppia : result.entrySet())
        {
            Manager m = context.getBean(Manager.class, coppia.getValue());
            ris.put(m.getId(), m);
        }
        return ris;
    }

    @Override
    public void update(Manager e , int...idModificato) 
    {
        database.executeUpdate(updatePersona, 
                                    e.getNome(),
                                    e.getCognome(),
                                    String.valueOf(e.getDataNascita()),
                                    String.valueOf(e.getId())
        );

        database.executeUpdate(updateManager, String.valueOf(idModificato), String.valueOf(e.getId())); //Se ci sono altre proprietà specifiche su dirigente aggiornaimo anche la sua tabella
    }

    @Override
    public void delete(Long id) 
    {
        database.executeUpdate(deletePersona, String.valueOf(id));
    }
    
}