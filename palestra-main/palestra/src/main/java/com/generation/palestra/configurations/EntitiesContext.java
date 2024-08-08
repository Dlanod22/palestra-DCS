package com.generation.palestra.configurations;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.generation.palestra.entities.Corso;
import com.generation.palestra.entities.Manager;
import com.generation.palestra.entities.PianoAbbonamento;
import com.generation.palestra.entities.Scheda;
import com.generation.palestra.entities.Istruttore;
import com.generation.palestra.entities.Cliente;

@Configuration
public class EntitiesContext {
    @Bean
    @Scope("prototype")
    public Cliente newCliente(Map<String, String> params){
        Cliente s = new Cliente();
        s.fromMap(params);

        return s;
    }

    @Bean
    @Scope("prototype")
    public Istruttore newIstruttore(Map<String, String> params){
        Istruttore d = new Istruttore();
        d.fromMap(params);

        return d;
    }

    @Bean
    @Scope("prototype")
    public Manager newManager(Map<String, String> params){
        Manager d = new Manager();
        d.fromMap(params);
        return d;
    }

    @Bean
    @Scope("prototype")
    public Corso newCorso(Map<String, String> params){
        Corso c = new Corso();
        c.fromMap(params);
        return c;
    }

    @Bean
    @Scope("prototype")
    public Scheda newScheda(Map<String, String> params){
        Scheda s = new Scheda();
        s.fromMap(params);

        return s;
    }

    @Bean
    @Scope("prototype")
    public PianoAbbonamento PianoAbbonamento(Map<String, String> params){
        PianoAbbonamento p = new PianoAbbonamento();
        p.fromMap(params);

        return p;
    }

}
