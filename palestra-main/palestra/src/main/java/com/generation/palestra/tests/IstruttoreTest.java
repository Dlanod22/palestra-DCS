package com.generation.palestra.tests;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.IstruttoreDao;
import com.generation.palestra.entities.Istruttore;

@Service
public class IstruttoreTest 
{
    @Autowired
        private IstruttoreDao istruttoreDao;  
         
        @Autowired
        public void test() 
        {
            System.out.println("-------------------INIZIO TEST ISTRUTTORE---------------------\n");
           
            System.out.println("\n-------------------ISTRUTTORE---------------------\n");
            if (istruttoreDao != null) {
                System.out.println(istruttoreDao.readAll());
                System.out.println(istruttoreDao.readByIdCorso(7L));

                // CREATE
                     /*Istruttore istruttore = new Istruttore();
                       istruttore.setNome("Angela");
                       istruttore.setCognome("Conte");
                       istruttore.setDataNascita(Date.valueOf("1994-11-12"));

                    // Inserisci l'istruttore nel database
                       Long id = istruttoreDao.create(istruttore);
                       System.out.println("\nIstruttore creato: " + istruttore + "\n");

               // UPDATE
                       Istruttore i = new Istruttore();
                       i.setId(15L);
                       i.setNome("paolo");
                       i.setCognome("Verde");
                       i.setDataNascita(Date.valueOf("1999-01-12"));
                       
                    // Inserisci l'istruttore nel database
                       istruttoreDao.update(i);
              
                        System.out.println("\nIstruttore Modificato : " + i + "\n");
          
               // DELETE
                        istruttoreDao.delete(15L);*/
                        
           
            } else {
                System.out.println("IstruttoreDao is null");
            }
    
            System.out.println("-------------------FINE TEST ISTRUTTORE------------------\n");        
        }
}
