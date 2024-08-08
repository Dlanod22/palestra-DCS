package com.generation.palestra.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.database.Database;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.entities.Entity;
import com.generation.palestra.entities.Istruttore;

@Service
public class IstruttoreDao implements IDAO<Istruttore>
{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    private final String insertPersona = "insert into persone(nome, cognome, data_nascita) values (?, ?, ?)";
 
    private final String insertIstruttore = "insert into istruttori(id) values(?)";

    private final String readAllIstruttore = "select * from persone p inner join istruttori i on p.id = i.id";

    private final String readIstruttoriByIdCorso = "select p.* from istruttori i join persone p on i.id = p.id join corsi c on i.id = c.id_istruttori where c.id = ?";

    private final String updatePersona = "update persone set nome=?, cognome=?, data_nascita=? where id=?";
 
    private final String updateIstruttore = "update istruttori set id=? where id=?";

    private final String deletePersona = "delete from persone where id=?";


    @Override
    public Long create(Istruttore e) 
    {
        Long idPersona = database.executeUpdate(insertPersona, 
                                    e.getNome(), 
                                    e.getCognome(), 
                                    e.getDataNascita().toString()
        );
        database.executeUpdate(insertIstruttore, 
                                    String.valueOf(idPersona)
        );

        return idPersona;
    }

    @Override
    public Map<Long, Entity> readAll() 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readAllIstruttore);
        for(Entry<Long, Map<String, String>> coppia : result.entrySet()){
            Istruttore i = context.getBean(Istruttore.class, coppia.getValue());

            ris.put(i.getId(), i);
        }

        return ris;
    }

    public Map<Long, Entity> readByIdCorso(Long idCorso)
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readIstruttoriByIdCorso, String.valueOf(idCorso));
        for(Entry<Long, Map<String, String>> coppia : result.entrySet())
        {
            Istruttore i = context.getBean(Istruttore.class, coppia.getValue());

            ris.put(i.getId(), i);
        }

        return ris;
    }

    @Override
    public void update(Istruttore e, int...idModificato) 
    {
        database.executeUpdate(updatePersona,
                                    e.getNome(),
                                    e.getCognome(),
                                    String.valueOf(e.getDataNascita()),
                                    String.valueOf(e.getId())
        );
        
        database.executeUpdate(updateIstruttore, String.valueOf(idModificato), String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) 
    {
        database.executeUpdate(deletePersona, String.valueOf(id));
    }

    
}

