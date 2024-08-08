package com.generation.palestra.entities;

import java.util.List;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Istruttore extends Persona
{
    private List<Corso> corsi;
}
