package com.generation.palestra.tests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.ManagerDao;
import com.generation.palestra.dao.PianoAbbonamentoDao;
import com.generation.palestra.dao.SchedaDao;
import com.generation.palestra.entities.PianoAbbonamento;
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

    //Se voglio che spring chiami un metodo in automatico, il metodo deve essere annotato con @Autowired e deve trovarsi all'interno
    //Di una classe BEAN di spring
     @Autowired
     public void test()
     {
       System.out.println("\n-------------------INIZIO TEST---------------------\n");

       System.out.println("\n-------------------SCHEDE---------------------\n");
       System.out.println(schedaDao.readAll());

       System.out.println("\n-------------------CORSO---------------------\n");
       System.out.println(corsoDao.readAll());

       System.out.println("\n-------------------PIANO ABBONAMENTO---------------------\n");
       System.out.println(pianoAbbonamentoDao.readAll());

       System.out.println("\n-------------------MANAGER---------------------\n");
       System.out.println(managerDao.readAll());

       System.out.println("\n-------------------CLIENTE---------------------\n");
       System.out.println(clienteDao.readAll());

       System.out.println("\n-------------------FINE TEST------------------\n");

     }

}
