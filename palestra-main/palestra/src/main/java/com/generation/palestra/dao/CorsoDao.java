package com.generation.palestra.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.database.Database;
import com.generation.palestra.entities.Corso;
import com.generation.palestra.entities.Entity;

@Service
public class CorsoDao implements IDAO<Corso>{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;


    private final String insertCorso = "insert into corsi(nome) values(?)";

    private final String readAllClassi = "select * from corsi";

    private final String updateCorso = "update corsi set nome=? where id=?";

    private final String deleteCorso = "delete from corsi where id=?";

    private final String readCorsiByIdIstruttore = "select c.* from corsi c  where c.id_istruttore = ?";

    @Override
    public Long create(Corso e) 
    {
        Long idCorso = database.executeUpdate(insertCorso, e.getNome());
        return idCorso;
    }

    @Override
    public Map<Long, Entity> readAll() 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readAllClassi);

        for(Entry<Long, Map<String, String>> coppia : result.entrySet()){
            Corso c = context.getBean(Corso.class, coppia.getValue());
            ris.put(c.getId(), c);
        }
        return ris;
    }

    @Override
    public void update(Corso e, int...idModificato) 
    {
        database.executeUpdate(updateCorso, e.getNome(), String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) 
    {
        database.executeUpdate(deleteCorso, String.valueOf(id));
    }


    //METODI EXTRA

    public Corso readById(Long id)
    {
        Map<Long, Entity> allClassi = readAll();
        return (Corso)allClassi.get(id);
    }

    public Map<Long, Entity> readByIdIstruttore(Long idIstruttore)
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readCorsiByIdIstruttore, String.valueOf(idIstruttore));

        for(Entry<Long, Map<String, String>> coppia : result.entrySet()){
            Corso c = context.getBean(Corso.class, coppia.getValue());
            ris.put(c.getId(), c);
        }
        return ris;
    }
    
}
