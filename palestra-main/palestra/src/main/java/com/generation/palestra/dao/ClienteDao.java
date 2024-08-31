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
import com.generation.palestra.entities.Scheda;


@Service
public class ClienteDao implements IDAO<Cliente>{

    @Autowired
    //@Qualifier("databaseMySql") //Questa annotazione serva a disambiguare qualora ci fosser più BEAN qualificati per questo @Autowired
    private Database database;

    @Autowired
    private PianoAbbonamentoDao pianoAbbonamentoDao;

    @Autowired
    private SchedaDao schedaDao;

    @Autowired
    private ApplicationContext context;


    private final String insertPersona = "insert into persone(nome, cognome, data_nascita) values (?, ?, ?)";
    
    private final String insertCliente = "insert into clienti(id, eta, peso, altezza, sesso, obiettivo, id_piano) values(?, ?, ?, ?, ?, ?, ?)";

    private final String readAllClienti = "select p.*, c.* from clienti c join persone p on c.id = p.id";

    private final String readClientiByIdPianoAbbonamento = "select p.*, c.* from clienti c join persone p on c.id = p.id where c.id_piano = ?";
    
    private final String readClientiByNomeLike = "select p.*, c.* from clienti c join persone p on c.id = p.id where p.nome like(concat('%', ?, '%'))";

    private final String readClientiByFilters = "select p.*, c.* from clienti c join persone p on c.id = p.id where p.nome like(concat('%', ?, '%')) AND c.id_piano = ? ";

    private final String updatePersona = "update persone set nome=?, cognome=?, data_nascita=? where id=?";

    private final String updateCliente = "update clienti set eta=?, peso=?, altezza=?, sesso=?, obiettivo=?, id_piano=? where id=?";

    private final String deletePersona = "delete from persone where id=?";

    private final String readClientiByIdCorso = "SELECT persone.*,clienti.* FROM clienti JOIN persone ON clienti.id = persone.id JOIN piani_abbonamento ON clienti.id_piano = piani_abbonamento.id JOIN piano_corsi ON piani_abbonamento.id = piano_corsi.piano_id JOIN corsi ON piano_corsi.corso_id = corsi.id WHERE corsi.id = ?;";

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

            Scheda s = schedaDao.readById((c.getId()));
            Scheda d = new Scheda();

            if(s != null)
            {
                c.setScheda(s);
            } 
            else
            {   
                d.setId(c.getId());
                d.setEse1("Questa");
                d.setEse2("Scheda");
                d.setEse3("è");
                d.setEse4("Vuota");
                d.setEse5("L'inserimento");
                d.setEse6("Viene");
                d.setEse7("Fatto");
                d.setEse8("Dall'");
                d.setEse9("Istruttore");
                d.setId_cliente(c.getId());
                c.setScheda(d);
                schedaDao.create(d);
                
            }

            if(idPianoStr != null)
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

            Scheda s = schedaDao.readById((c.getId()));
            Scheda d = new Scheda();

            if(s != null)
            {
                c.setScheda(s);
            } 
            else
            {   
                d.setId(c.getId());
                d.setEse1("Questa");
                d.setEse2("Scheda");
                d.setEse3("è");
                d.setEse4("Vuota");
                d.setEse5("L'inserimento");
                d.setEse6("Viene");
                d.setEse7("Fatto");
                d.setEse8("Dall'");
                d.setEse9("Istruttore");
                d.setId_cliente(c.getId());
                c.setScheda(d);
                schedaDao.create(d);
                
            }

            ris.put(c.getId(), c);
        }
        
        return ris;
    }



    public Map<Long, Entity> readByIdCorso(Long id_corso) 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readClientiByIdCorso, String.valueOf(id_corso));

        for(Entry<Long, Map<String, String>> coppia : result.entrySet())
        {
            Cliente c = context.getBean(Cliente.class, coppia.getValue());
            PianoAbbonamento p = pianoAbbonamentoDao.readById(Long.parseLong(coppia.getValue().get("idpiano")));
            c.setPianoAbbonamento(p);

            Scheda s = schedaDao.readById((c.getId()));
            Scheda d = new Scheda();

            if(s != null)
            {
                c.setScheda(s);
            } 
            else
            {   
                d.setId(c.getId());
                d.setEse1("Questa");
                d.setEse2("Scheda");
                d.setEse3("è");
                d.setEse4("Vuota");
                d.setEse5("L'inserimento");
                d.setEse6("Viene");
                d.setEse7("Fatto");
                d.setEse8("Dall'");
                d.setEse9("Istruttore");
                d.setId_cliente(c.getId());
                c.setScheda(d);
                schedaDao.create(d);
                
            }

            ris.put(c.getId(), c);
        }
        
        return ris;
    }

    public Map<Long, Entity> readByFilters(String nome, Long id_piano) 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = null;

        //Controllo se ho il filtro sul piano abbonamento oppure no controllando se il suo valore è uguale a zero (zero abbiamo deciso che significa "tutti i pianiAbbonamento").
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
    public void update(Cliente e) 
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