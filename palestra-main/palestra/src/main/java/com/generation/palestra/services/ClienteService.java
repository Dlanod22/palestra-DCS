package com.generation.palestra.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.generation.palestra.dao.ClienteDao;
import com.generation.palestra.entities.Entity;
import com.generation.palestra.entities.Cliente;

@Service
public class ClienteService extends GenericService<Cliente, ClienteDao>
{
    
    public List<Cliente> readByIdPiano(Long idPiano)
    {
        Map<Long, Entity> clienti = getRepository().readByIdPiano(idPiano);
        List<Cliente> listaClienti = new ArrayList<>();
        
        for(Entity e : clienti.values())
        {
            Cliente s = (Cliente)e;
            listaClienti.add(s);
        }
        return listaClienti;
    }

    public List<Cliente> readByIdCorso(Long idCorso)
    {
        Map<Long, Entity> clienti = getRepository().readByIdCorso(idCorso);
        List<Cliente> listaClienti = new ArrayList<>();
        
        for(Entity e : clienti.values())
        {
            Cliente s = (Cliente)e;
            listaClienti.add(s);
        }
        return listaClienti;
    }



    public List<Cliente> readByNomeLike(String nome)
    {
        Map<Long, Entity> clienti = getRepository().readByFilters(nome, 0L);

        return clienti.values().stream().map(e -> (Cliente)e).toList();
    }


    public List<Cliente> readByFilters(String nome, Long idPiano)
    {
        Map<Long, Entity> studenti = getRepository().readByFilters(nome, idPiano);

        return studenti.values().stream().map(e -> (Cliente)e).toList();
    }

    


}
