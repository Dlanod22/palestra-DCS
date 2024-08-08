package com.generation.palestra.entities;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Cliente extends Persona
{

    private int eta;

    private double peso;

    private int altezza;

    private char sesso;

    private String obiettivo;

    private PianoAbbonamento pianoAbbonamento;
    private Scheda scheda;

    
}
