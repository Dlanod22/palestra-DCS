package com.generation.palestra.services;

import org.springframework.stereotype.Service;

import com.generation.palestra.dao.CorsoDao;
import com.generation.palestra.entities.Corso;

@Service
public class CorsoService extends GenericService<Corso, CorsoDao>{

    @Override
    public void createOrUpdateUser(Long id, String username, String password) {
        throw new UnsupportedOperationException("Metodo non supportato su ClasseService");   
    }
    
    
}