package com.generation.palestra.services;

import org.springframework.stereotype.Service;

import com.generation.palestra.dao.PianoAbbonamentoDao;
import com.generation.palestra.entities.PianoAbbonamento;

@Service
public class PianoAbbonamentoService extends GenericService<PianoAbbonamento, PianoAbbonamentoDao>
{
    @Override
    public void createOrUpdateUser(Long id, String username, String password) 
    {
        throw new UnsupportedOperationException("Metodo non supportato su PianoAbbonamentoService");   
    }
}
