package com.generation.palestra;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.generation.palestra.tests.TestCliente;


@SpringBootApplication
public class PalestraApplication {

    public static void main(String[] args) 
	{
        SpringApplication.run(PalestraApplication.class, args);
        
        TestCliente testC = new TestCliente();

        testC.test();
	

    }
}
