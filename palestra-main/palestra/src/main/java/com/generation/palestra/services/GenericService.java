package com.generation.palestra.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.generation.palestra.dao.IDAO;
import com.generation.palestra.dao.UserDao;
import com.generation.palestra.entities.Entity;

import lombok.Getter;

@Getter
public abstract class GenericService<E extends Entity, D extends IDAO<E>> 
{
    
    @Autowired
    private D repository;

    @Autowired
    private UserDao userDAO;


    public List<E> readAll()
    {
        Map<Long, Entity> result = repository.readAll();
        List<E> ris = new ArrayList<>();

        for(Entity entity : result.values())
        {
            ris.add((E)entity);
        }

        return ris;
    }

    public E readById(Long id)
    {
        Map<Long, Entity> result = repository.readAll();
        E e = (E)result.get(id);
        return e;
    }

    public void update(E e)
    {
        repository.update(e);
    }

    public void delete(Long id)
    {
        repository.delete(id);
    }

    public Long create(E e)
    {
        return repository.create(e);
    }

    public void createOrUpdateUser(Long id, String username, String password)
    {
        userDAO.updateUsernameAndPassword(id, username, password);  
    }
}
