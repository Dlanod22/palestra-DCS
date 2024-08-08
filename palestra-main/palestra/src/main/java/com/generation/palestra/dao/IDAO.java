package com.generation.palestra.dao;

import java.util.Map;

import com.generation.palestra.entities.Entity;

public interface IDAO<E extends Entity> 
{
    public Long create(E e);

    public Map<Long, Entity> readAll();

    public void update(E e, int...i);

    public void delete(Long id);
}