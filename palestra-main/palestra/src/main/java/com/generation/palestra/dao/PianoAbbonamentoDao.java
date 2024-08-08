package com.generation.palestra.dao;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.database.Database;
import com.generation.palestra.entities.Entity;
import com.generation.palestra.entities.PianoAbbonamento;
import com.generation.palestra.entities.Scheda;

@Service
public class PianoAbbonamentoDao implements IDAO<PianoAbbonamento> {
       @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    private final String insertPianoAbbonamento = "insert into piani_abbonamento(nome) values(?)";

    private final String ReadAllAbbonamenti = "select * from piani_abbonamento";

    private final String updateAbbonamento = "update piani_abbonamento set nome=? where id=?";

    private final String deleteAbbonamento = "delete from piani_abbonamento where id=?";

    @Override
    public Long create(PianoAbbonamento e) 
    {
        Long idScheda = database.executeUpdate(insertPianoAbbonamento, e.getNome());
        return idScheda;
    }

    @Override
    public Map<Long, Entity> readAll() 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(ReadAllAbbonamenti);

        for(Entry<Long, Map<String, String>> coppia : result.entrySet()){
            PianoAbbonamento p = context.getBean(PianoAbbonamento.class, coppia.getValue());
            ris.put(p.getId(), p);
        }
        return ris;
    }

    public PianoAbbonamento readById(Long id)
    {
        Map<Long, Entity> allAbbonamenti = readAll();
        return (PianoAbbonamento)allAbbonamenti.get(id);
    }

    

    @Override
    public void update(PianoAbbonamento e, int...idModificato) 
    {
        database.executeUpdate(updateAbbonamento, e.getNome(), String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) 
    {
        database.executeUpdate(deleteAbbonamento, String.valueOf(id));
    }
}
