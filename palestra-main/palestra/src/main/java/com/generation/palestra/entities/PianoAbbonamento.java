package com.generation.palestra.entities;
import java.util.List;

import lombok.Data;
import lombok.ToString;
@ToString(callSuper = true)
@Data
public class PianoAbbonamento extends Entity
{

private String nome;
private List<Corso> corsi;

}
