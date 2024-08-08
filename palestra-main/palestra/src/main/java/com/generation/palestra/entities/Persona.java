package com.generation.palestra.entities;

import java.sql.Date;

import lombok.Data;

@Data
public abstract class Persona extends Entity
{
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String username;
    private String password;
}
