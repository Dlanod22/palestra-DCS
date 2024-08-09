package com.generation.palestra.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.ManagerDao;
import com.generation.palestra.dao.PianoAbbonamentoDao;
import com.generation.palestra.dao.SchedaDao;
import com.generation.palestra.entities.Corso;
import com.generation.palestra.dao.ClienteDao;
import com.generation.palestra.dao.CorsoDao;

@Service
public class ClienteTest {

    @Autowired
    private SchedaDao schedaDao;

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private ClienteDao clienteDao;

    @Autowired
    private CorsoDao corsoDao;

    @Autowired
    private PianoAbbonamentoDao pianoAbbonamentoDao;

    
    @Autowired
    public void test() {
        System.out.println("-------------------TEST---------------------\n");

        System.out.println("\n-------------------SCHEDE---------------------\n");
        if (schedaDao != null) {
            System.out.println(schedaDao.readAll());
        } else {
            System.out.println("schedaDao is null");
        }

        System.out.println("\n-------------------MANAGER---------------------\n");
        if (managerDao != null) {
            System.out.println(managerDao.readAll());
        } else {
            System.out.println("managerDao is null");
        }

        System.out.println("\n-------------------CLIENTE---------------------\n");
        if (clienteDao != null) {
            System.out.println(clienteDao.readAll());
        } else {
            System.out.println("clienteDao is null");
        }

        System.out.println("\n-------------------CORSO---------------------\n");
        if (corsoDao != null) {
            System.out.println(corsoDao.readAll());
            System.out.println(corsoDao.readById(3L));
                    /*Corso corso = new Corso();
                    corso.setId(2L);
                    corso.setNome("Corso di Pilates");
                    
                    corsoDao.update(corso);
                    System.out.println(corsoDao.readById(2L));
                    corsoDao.delete(1L);
                    Corso c = new Corso();
                    c.setId(11L);
                    c.setNome("Corso java");
                    c.setIdIstruttore(13L);
                    corsoDao.create(c);*/
                   
         System.out.println("\n-------------------PIANO ABBONAMENTO---------------------\n");
        System.out.println(pianoAbbonamentoDao.readAll());

        System.out.println("-------------------FINE TEST------------------\n");
    }
}
}


