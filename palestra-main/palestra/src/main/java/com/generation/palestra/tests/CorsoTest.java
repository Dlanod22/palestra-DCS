package com.generation.palestra.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.CorsoDao;
import com.generation.palestra.entities.Corso;

@Service
public class CorsoTest 
{

     @Autowired
        private CorsoDao corsoDao;

 @Autowired
        public void test() 
        {
            System.out.println("-------------------INIZIO TEST CORSO---------------------\n");
           
            if (corsoDao != null) 
            {
                System.out.println(corsoDao.readAll());
                System.out.println(corsoDao.readById(3L));

               /*    // Update
                        /*Corso corso = new Corso();
                        corso.setId(2L);
                        corso.setNome("Corso di Pilates");
                        corsoDao.update(corso);
                        System.out.println(corsoDao.readById(2L));
                        
                   // DELETE     
                        corsoDao.delete(1L);
                       
                   // CREATE     
                        Corso c = new Corso();
                        c.setId(11L);
                        c.setNome("Corso java");
                        c.setIdIstruttore(15L);
                        corsoDao.create(c);*/

                System.out.println("-------------------FINE TEST CORSO------------------\n");        
            }
        }
}
