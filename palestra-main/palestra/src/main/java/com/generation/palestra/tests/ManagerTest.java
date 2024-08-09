package com.generation.palestra.tests;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.ManagerDao;
import com.generation.palestra.entities.Manager;

@Service
public class ManagerTest 
{
  @Autowired
         private ManagerDao managerDao;


        @Autowired
        public void test() 
        {
            System.out.println("-------------------INIZIO TEST MANAGER---------------------\n");

            System.out.println("\n-------------------MANAGER---------------------\n");
                if (managerDao != null) 
                {
                    System.out.println(managerDao.readAll());
                // CREATE
                       /*Manager m = new Manager();
                       m.setNome("Donald");
                       m.setCognome("Guga");
                       m.setDataNascita(Date.valueOf("1983-01-22"));

                    // Inserisci l'istruttore nel database
                       Long id = managerDao.create(m);
                       System.out.println("\nManager creato: " + m + "\n");

                // UPDATE
                        Manager m = new Manager();
                        m.setNome("Kerdj");
                        m.setCognome("Asslani");
                        m.setDataNascita(Date.valueOf("2000-01-22"));
                        m.setId(14L);

                    // Inserisci l'istruttore nel database
                        managerDao.update(m);
                        System.out.println("\nManager creato: " + m + "\n");
                    
                // DELETE
                        managerDao.delete(14L);*/

                } else {
                System.out.println("managerDao is null");
                }
        }
}        
