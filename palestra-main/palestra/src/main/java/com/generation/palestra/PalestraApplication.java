package com.generation.palestra;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.generation.palestra.tests.TestCliente;

import com.generation.palestra.dao.ClienteDao;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.PianoAbbonamento;
import com.generation.palestra.tests.CorsoTest;
import com.generation.palestra.tests.IstruttoreTest;
import com.generation.palestra.tests.ManagerTest;
import com.generation.palestra.tests.PianoAbbonamentoTest;
import com.generation.palestra.tests.SchedaTest;

@SpringBootApplication
public class PalestraApplication {

    public static void main(String[] args) 
	{
        SpringApplication.run(PalestraApplication.class, args);
        
		TestCliente test1 = new TestCliente();
        test1.test();

        CorsoTest test2 = new CorsoTest();
        test2.test();

        IstruttoreTest test3 = new IstruttoreTest();
        test3.test();

        ManagerTest test4 = new ManagerTest();
        test4.test();

        PianoAbbonamentoTest test5 = new PianoAbbonamentoTest();
        test5.test();

        SchedaTest test6 = new SchedaTest();
        test6.test();
		
    }
}
