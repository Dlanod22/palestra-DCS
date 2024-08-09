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

            if (schedaDao != null) 
            {
               /* System.out.println(schedaDao.readAll());
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
                    s.setId(11L);

                // Inserisci l'istruttore nel database
                   Long id = schedaDao.create(s);
                   System.out.println("\nScheda creata: " + s + "\n");

            // UPDATE
                    Scheda s = new Scheda();
                    s.setEse1("Bello");
                    s.setEse2("di");
                    s.setEse3("fai");
                    s.setEse4("che");
                    s.setEse5("bene");
                    s.setEse6("sto");
                    s.setEse7("io");
                    s.setEse8("stai");
                    s.setEse9("come");
                    s.setId(11L);

                // Inserisci l'istruttore nel database
                   schedaDao.update(s);
                   System.out.println("\nScheda creato: " + s + "\n");
                
            // DELETE
                    schedaDao.delete(11L);

            } else {
            System.out.println("Scheda is null");*/
            }

            System.out.println("-------------------FINE TEST SCHEDA---------------------\n");

    }
}
