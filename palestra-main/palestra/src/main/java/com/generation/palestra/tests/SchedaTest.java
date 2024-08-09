package com.generation.palestra.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.SchedaDao;
import com.generation.palestra.entities.Scheda;

@Service
public class SchedaTest 
{
    @Autowired
    private SchedaDao schedaDao;


    @Autowired
    public void test() 
    {
        System.out.println("-------------------INIZIO TEST SCHEDA---------------------\n");

        System.out.println("\n-------------------SCHEDA---------------------\n");
            if (schedaDao != null) 
            {
                System.out.println(schedaDao.readAll());
                System.out.println(schedaDao.readById(1L));
            // CREATE
                    Scheda s = new Scheda();
                    s.setEse1("CIAO");
                    s.setEse2("COME");
                    s.setEse3("STAI");
                    s.setEse4("IO");
                    s.setEse5("BENE");
                    s.setEse6("CHE");
                    s.setEse7("FAI");
                    s.setEse8("DI");
                    s.setEse9("BELLO");

                // Inserisci l'istruttore nel database
                   Long id = schedaDao.create(s);
                   System.out.println("\nScheda creata: " + s + "\n");

            // UPDATE
                    /*PianoAbbonamento p = new PianoAbbonamento();
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
    }
}
