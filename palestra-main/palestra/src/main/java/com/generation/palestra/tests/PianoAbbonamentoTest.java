package com.generation.palestra.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.PianoAbbonamentoDao;
import com.generation.palestra.entities.Corso;
import com.generation.palestra.entities.PianoAbbonamento;



@Service
public class PianoAbbonamentoTest 
{

    @Autowired
    private PianoAbbonamentoDao pianoAbbonamentoDao;

 @Autowired
        public void test() 
        {
            System.out.println("-------------------INIZIO TEST PIANOABBONAMENTO---------------------\n");

                if (pianoAbbonamentoDao != null) 
                {
                    System.out.println(pianoAbbonamentoDao.readAll());
                    System.out.println(pianoAbbonamentoDao.readById(1L));
                /*// CREATE
                        PianoAbbonamento p = new PianoAbbonamento();
                        p.setNome("Super piano DELUXE");
                        p.setId(4L);

                    // Inserisci l'istruttore nel database
                       Long id = pianoAbbonamentoDao.create(p);
                       System.out.println("\nPianoAbbonamento creato: " + p + "\n");

                // UPDATE
                        PianoAbbonamento p = new PianoAbbonamento();
                        p.setNome("Super piano incredibile DELUXE");
                        p.setId(4L);

                    // Inserisci l'istruttore nel database
                       pianoAbbonamentoDao.update(p);
                       System.out.println("\nPianoAbbonamento creato: " + p + "\n");
                    
                // DELETE
                        pianoAbbonamentoDao.delete(4L);*/

                } else {
                System.out.println("PianoAbbonamentoDao is null");
                }

                System.out.println("-------------------FINE TEST PIANOABBONAMENTO---------------------\n");
        }
}
