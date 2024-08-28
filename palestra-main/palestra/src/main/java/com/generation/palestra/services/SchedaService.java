package com.generation.palestra.services;

import com.generation.palestra.dao.SchedaDao;
import com.generation.palestra.entities.Scheda;

public class SchedaService extends GenericService<Scheda, SchedaDao>
{

        @Override
        public void createOrUpdateUser(Long id, String username, String password) 
        {
            throw new UnsupportedOperationException("Metodo non supportato su SchedaService");   
        }
}
