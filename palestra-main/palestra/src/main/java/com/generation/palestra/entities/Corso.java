package com.generation.palestra.entities;


import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Corso extends Entity
{
    private String nome;
}