package com.generation.palestra.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.thymeleaf.exceptions.ParserInitializationException;

import com.generation.palestra.dao.database.Database;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.entities.Entity;
import com.generation.palestra.entities.PianoAbbonamento;

@Service
public class ClienteDao implements IDAO<Cliente>{

    @Autowired
    //@Qualifier("databaseMySql") //Questa annotazione serva a disambiguare qualora ci fosser più BEAN qualificati per questo @Autowired
    private Database database;

    @Autowired
    private PianoAbbonamentoDao pianoAbbonamentoDao;

    @Autowired
    private ApplicationContext context;


    private final String insertPersona = "insert into persone(nome, cognome, data_nascita) values (?, ?, ?)";
    
    private final String insertCliente = "insert into clienti(id, eta, peso, altezza, sesso, obiettivo, id_piano) values(?, ?, ?, ?, ?, ?, ?)";

    private final String readAllClienti = "select p.*, c.* from clienti c join persone p on c.id = p.id";

    private final String readClientiByIdPianoAbbonamento = "select p.*, c.id_piano from clienti c join persone p on c.id = p.id where c.id_piano = ?";
    
    private final String readClientiByNomeLike = "select p.*, c.id_piano from clienti c join persone p on c.id = p.id where p.nome like(concat('%', ?, '%'))";

    private final String readClientiByFilters = "select p.*, c.id_piano from clienti c join persone p on c.id = p.id where p.nome like(concat('%', ?, '%')) AND c.id_piano = ? ";

    private final String updatePersona = "update persone set nome=?, cognome=?, data_nascita=? where id=?";

    private final String updateCliente = "update clienti set eta=?, peso=?, altezza=?, sesso=?, obiettivo=?, id_piano=? where id=?";

    private final String deletePersona = "delete from persone where id=?";

    @Override
    public Long create(Cliente e) 
    {
        Long idPersona = database.executeUpdate(insertPersona, 
                                    e.getNome(), 
                                    e.getCognome(), 
                                    e.getDataNascita().toString()
        );
        database.executeUpdate(insertCliente, 
                                    String.valueOf(idPersona),
                                    String.valueOf(e.getEta()),
                                    String.valueOf(e.getPeso()),
                                    String.valueOf(e.getAltezza()),
                                    String.valueOf(e.getSesso()),
                                    e.getObiettivo(),
                                    e.getPianoAbbonamento() != null ? String.valueOf(e.getPianoAbbonamento().getId()) : null
        );

        return idPersona;
    }

    @Override
    public Map<Long, Entity> readAll() 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readAllClienti);
    
        for (Entry<Long, Map<String, String>> coppia : result.entrySet()) 
        {
            Cliente c = context.getBean(Cliente.class, coppia.getValue());
            String idPianoStr = coppia.getValue().get("idpiano");

            if(idPianoStr == null)
            {
                PianoAbbonamento p = pianoAbbonamentoDao.readById(Long.parseLong(idPianoStr));
                c.setPianoAbbonamento(p);
            }
    
            ris.put(c.getId(), c);
        }
    
        return ris;
    }
    public Map<Long, Entity> readByIdPiano(Long id_piano) 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readClientiByIdPianoAbbonamento, String.valueOf(id_piano));

        for(Entry<Long, Map<String, String>> coppia : result.entrySet())
        {
            Cliente c = context.getBean(Cliente.class, coppia.getValue());
            PianoAbbonamento p = pianoAbbonamentoDao.readById(Long.parseLong(coppia.getValue().get("idpiano")));
            c.setPianoAbbonamento(p);
            ris.put(c.getId(), c);
        }
        
        return ris;
    }

    public Map<Long, Entity> readByFilters(String nome, Long id_piano) 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = null;

        //Controllo se ho il filtro sulla classe oppure no controllando se il suo valore è uguale a zero (zero abbiamo deciso che significa "tutti i pianiAbbonamento").
        //se è zero applico la query che filtra solo con il 'like' sul nome altrimenti applico quella che utilizza entrambi i filtri
        if(id_piano == 0)
        {
            result = database.executeQuery(readClientiByNomeLike, nome);
        }
        else if(id_piano > 0)
        {
            result = database.executeQuery(readClientiByFilters, nome, String.valueOf(id_piano));
        }

        for(Entry<Long, Map<String, String>> coppia : result.entrySet())
        {
            Cliente c = context.getBean(Cliente.class, coppia.getValue());
            PianoAbbonamento p = pianoAbbonamentoDao.readById(Long.parseLong(coppia.getValue().get("idpiano")));
            c.setPianoAbbonamento(p);
            ris.put(c.getId(), c);
        }
        
        return ris;
    }

    @Override
    public void update(Cliente e, int...idModificato) 
    {
        database.executeUpdate(updatePersona,
                                    e.getNome(),
                                    e.getCognome(),
                                    String.valueOf(e.getDataNascita()),
                                    String.valueOf(e.getId())
        );

        Long pianoAbbonamentoId = null;
        if (e.getPianoAbbonamento() != null) 
        {
        pianoAbbonamentoId = e.getPianoAbbonamento().getId();
        }
        
        database.executeUpdate(updateCliente, 
                                    String.valueOf(e.getEta()),
                                    String.valueOf(e.getPeso()),
                                    String.valueOf(e.getAltezza()),
                                    String.valueOf(e.getSesso()),
                                    e.getObiettivo(),
                                    pianoAbbonamentoId != null ? String.valueOf(pianoAbbonamentoId) : null,
                                    String.valueOf(e.getId())
                                );                  
    }

    @Override
    public void delete(Long id) 
    {
        database.executeUpdate(deletePersona, String.valueOf(id));
    }
    
}