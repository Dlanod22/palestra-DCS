package com.generation.palestra.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.CorsoDao;
import com.generation.palestra.dao.IstruttoreDao;
import com.generation.palestra.entities.Corso;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Entity;

@Service
public class IstruttoreService extends GenericService<Istruttore, IstruttoreDao>{

    @Autowired
    private CorsoDao corsoDAO;

    @Override
    public List<Istruttore> readAll() 
    {
        Map<Long, Entity> docenti = getRepository().readAll();
        List<Istruttore> listaIstruttori = new ArrayList<>();
        
        for(Entity e : docenti.values())
        {
            Istruttore d = (Istruttore)e;
            Map<Long, Entity> corsi = corsoDAO.readByIdIstruttore(d.getId());
            List<Corso> listaCorsi = new ArrayList<>();

            for(Entity cls : corsi.values())
            {
                listaCorsi.add((Corso)cls);
            }
            
            d.setCorsi(listaCorsi);

            listaIstruttori.add(d);
        }
        return listaIstruttori;
    }

    @Override
    public Istruttore readById(Long id) 
    {
        Map<Long, Entity> docenti = getRepository().readAll();
        Istruttore d = (Istruttore)docenti.get(id);

        if(d != null)
        {
            Map<Long, Entity> classi = corsoDAO.readByIdIstruttore(d.getId());
            List<Corso> listaClassi = new ArrayList<>();

            for(Entity cls : classi.values())
            {
                listaClassi.add((Corso)cls);
            }
                
            d.setCorsi(listaClassi);
        }

        return d;
    }

    
    public List<Istruttore> readByIdCorso(Long idCorso)
    {
        Map<Long, Entity> istruttori = getRepository().readByIdCorso(idCorso);
        List<Istruttore> listaIstruttori = new ArrayList<>();

        for(Entity e : istruttori.values())
        {
            Istruttore d = (Istruttore)e;
            Map<Long, Entity> classi = corsoDAO.readByIdIstruttore(d.getId());
            List<Corso> listaClassi = new ArrayList<>();

            for(Entity cls : classi.values())
            {
                listaClassi.add((Corso)cls);
            }
            
            d.setCorsi(listaClassi);

            listaIstruttori.add(d);
        }
        return listaIstruttori;
    }

    
}