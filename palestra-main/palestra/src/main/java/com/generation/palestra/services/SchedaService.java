package com.generation.palestra.services;

import org.springframework.stereotype.Service;

import com.generation.palestra.dao.SchedaDao;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.entities.Scheda;

@Service
public class SchedaService extends GenericService<Scheda, SchedaDao>
{

        
    public Scheda getSchedaConsigliata(Cliente cliente) 
    {
        Scheda scheda = new Scheda();

        scheda.setId(cliente.getId());

        if (cliente.getPeso() <= 51) {
            if (cliente.getObiettivo().equalsIgnoreCase("Perdere peso")) {
                scheda.setEse1("Corsa leggera");
                scheda.setEse2("Jumping Jacks");
                scheda.setEse3("Mountain Climbers");
                scheda.setEse4("Burpees");
                scheda.setEse5("Affondi in movimento");
                scheda.setEse6("Plank");
                scheda.setEse7("High Knees");
                scheda.setEse8("Squat a corpo libero");
                scheda.setEse9("Crunch");
            } else if (cliente.getObiettivo().equalsIgnoreCase("Tonificare")) {
                scheda.setEse1("Affondi frontali");
                scheda.setEse2("Push-up");
                scheda.setEse3("Russian Twist");
                scheda.setEse4("Sollevamento gambe");
                scheda.setEse5("Squat con salto");
                scheda.setEse6("Spinte con manubri");
                scheda.setEse7("Deadlift con manubri");
                scheda.setEse8("Leg curl");
                scheda.setEse9("Side Plank per lato");
            } else if (cliente.getObiettivo().equalsIgnoreCase("Aumentare massa")) {
                scheda.setEse1("Squat con bilanciere");
                scheda.setEse2("Panca piana");
                scheda.setEse3("Rematore con manubrio per lato");
                scheda.setEse4("Military press");
                scheda.setEse5("Curl con bilanciere");
                scheda.setEse6("Estensioni tricipiti sopra la testa");
                scheda.setEse7("Leg Press");
                scheda.setEse8("Calf raises");
                scheda.setEse9("Crunch con peso");
            } else if (cliente.getObiettivo().equalsIgnoreCase("Mantenere peso")) {
                scheda.setEse1("Corsa moderata");
                scheda.setEse2("Circuito di squat, push-up");
                scheda.setEse3("Affondi indietro per gamba");
                scheda.setEse4("Plank laterale per lato");
                scheda.setEse5("Jumping Jacks");
                scheda.setEse6("Sollevamento gambe");
                scheda.setEse7("Crunch obliqui per lato");
                scheda.setEse8("Russian Twist");
                scheda.setEse9("Squat a corpo libero");
            }
        } else if (cliente.getPeso() > 51 && cliente.getPeso() <= 230) {
            if (cliente.getObiettivo().equalsIgnoreCase("Perdere peso")) {
                scheda.setEse1("Corsa su tapis roulant");
                scheda.setEse2("Step-up su panca per gamba");
                scheda.setEse3("Mountain Climbers");
                scheda.setEse4("Burpees");
                scheda.setEse5("Jumping Jacks");
                scheda.setEse6("Affondi in movimento per gamba");
                scheda.setEse7("High Knees");
                scheda.setEse8("Squat");
                scheda.setEse9("Plank");
            } else if (cliente.getObiettivo().equalsIgnoreCase("Tonificare")) {
                scheda.setEse1("Circuito di squat, push-up");
                scheda.setEse2("Affondi laterali");
                scheda.setEse3("Push-up sulle ginocchia");
                scheda.setEse4("Plank laterale per lato");
                scheda.setEse5("Crunch a bicicletta per lato");
                scheda.setEse6("Spinte con manubri");
                scheda.setEse7("Deadlift con kettlebell");
                scheda.setEse8("Leg curl");
                scheda.setEse9("Side Plank per lato");
            } else if (cliente.getObiettivo().equalsIgnoreCase("Aumentare massa")) {
                scheda.setEse1("Squat con bilanciere");
                scheda.setEse2("Panca inclinata");
                scheda.setEse3("Rematore con manubrio per lato");
                scheda.setEse4("Military press con manubri");
                scheda.setEse5("Curl con manubri");
                scheda.setEse6("Estensioni tricipiti sopra la testa");
                scheda.setEse7("Leg Press");
                scheda.setEse8("Calf raises");
                scheda.setEse9("Russian Twist con peso");
            } else if (cliente.getObiettivo().equalsIgnoreCase("Mantenere peso")) {
                scheda.setEse1("Corsa moderata");
                scheda.setEse2("Affondi indietro");
                scheda.setEse3("Push-up");
                scheda.setEse4("Squat a corpo libero");
                scheda.setEse5("Russian Twist");
                scheda.setEse6("Spinte con manubri");
                scheda.setEse7("Deadlift con manubri");
                scheda.setEse8("Calf raises");
                scheda.setEse9("Plank");
            }
        }

        scheda.setId_cliente(cliente.getId());


        return scheda;
    }
}
