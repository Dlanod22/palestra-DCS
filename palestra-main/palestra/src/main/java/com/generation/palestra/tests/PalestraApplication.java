package com.generation.palestra.tests;

import java.sql.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.generation.palestra.dao.ClienteDao;
import com.generation.palestra.entities.Cliente;

@SpringBootApplication
@ComponentScan(basePackages = "com.generation.palestra")
public class PalestraApplication {

    public static void main(String[] args) 
	{
        SpringApplication.run(PalestraApplication.class, args);
        
		ClienteTest test = new ClienteTest();

        test.test();
		
		
		/*ClienteDao clienteDao = context.getBean(ClienteDao.class);

        // Crea un nuovo cliente
        Cliente cliente = new Cliente();
        cliente.setNome("Mario");
        cliente.setCognome("Rossi");
        cliente.setDataNascita(Date.valueOf("1980-01-01"));
        cliente.setEta(40);
        cliente.setPeso(70.5);
        cliente.setAltezza(175);
        cliente.setSesso('M');
        cliente.setObiettivo("Perdere peso");

        // Inserisci il cliente nel database
        Long id = clienteDao.create(cliente);

        // Modifica i dati del cliente
        cliente.setId(id);
        cliente.setNome("Luigi");
        cliente.setCognome("Verdi");
        cliente.setEta(45);
        cliente.setPeso(75.0);
        cliente.setAltezza(180);
        cliente.setObiettivo("Mantenere peso");

        // Esegui l'update del cliente
        clienteDao.update(cliente);

        // Stampa i dati aggiornati del cliente
        Cliente clienteAggiornato = (Cliente) clienteDao.readAll().get(id);
        System.out.println(clienteAggiornato);*/
		

    }
}
