package com.generation.palestra.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class Entity implements IMappable
{
    private Long id;
}