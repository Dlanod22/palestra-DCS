package com.generation.palestra.tests;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.ClienteDao;
import com.generation.palestra.dao.CorsoDao;
import com.generation.palestra.dao.ManagerDao;
import com.generation.palestra.dao.PianoAbbonamentoDao;
import com.generation.palestra.dao.SchedaDao;
import com.generation.palestra.entities.Cliente;

@Service
public class TestCliente 
{


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
    public void test() 
    {
      
        System.out.println("\n-------------------INIZIO TEST CLIENTE---------------------\n");
        
        //LEGGI TUTTI I CLIENTI
        /* 
        if (clienteDao != null) {
            System.out.println(clienteDao.readAll());
        } else {
            System.out.println("clienteDao is null");
        } */



        //LEGGI CLIENTI IN BASE AL PIANO ABBONAMENTO SCELTO
        /* 
        if (clienteDao != null) {
            System.out.println(clienteDao.readByIdPiano(1L));
        } else {
            System.out.println("clienteDao is null");
        } */



         //LEGGI CLIENTI IN BASE A NOME E 
        /*
        if (clienteDao != null) 
        {
            System.out.println(clienteDao.readByFilters("Paolo", (0L)));
        } else 
        {
            System.out.println("clienteDao is null");
        } */



        // CREA NUOVO CLIENTE
    
        /*Cliente cliente = new Cliente();
        cliente.setNome("Giucas");
        cliente.setCognome("Casella");
        cliente.setDataNascita(Date.valueOf("1980-01-01"));
        cliente.setEta(40);
        cliente.setPeso(70.5);
        cliente.setAltezza(175);
        cliente.setSesso('M');
        cliente.setObiettivo("Perdere peso");
        cliente.setPianoAbbonamento(pianoAbbonamentoDao.readById(1L));

        // Inserisci il cliente nel database
        Long id = clienteDao.create(cliente);
        System.out.println("\nCliente creato: " + cliente + "\n");



        // MODIFICA DATI CLIENTE
        cliente.setId(id);
        cliente.setNome("Mino");
        cliente.setCognome("Prima");
        cliente.setDataNascita(Date.valueOf("1956-01-01"));
        cliente.setEta(45);
        cliente.setPeso(75.0);
        cliente.setAltezza(180);
        cliente.setObiettivo("Mantenere peso");
        cliente.setPianoAbbonamento(pianoAbbonamentoDao.readById(2L));

        // Esegui l'update del cliente
        clienteDao.update(cliente);

        // Stampa i dati aggiornati del cliente
        System.out.println("\nCliente aggiornato: " + cliente + "\n");
        Cliente clienteAggiornato = (Cliente)clienteDao.readAll().get(id);
        System.out.println(clienteAggiornato); */



        //ELIMINA CLIENTE
        //clienteDao.delete(18L);



        System.out.println("-------------------FINE TEST CLIENTE------------------\n");
    }


}
