package com.generation.palestra.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.generation.palestra.dao.database.Database;
import com.generation.palestra.entities.Cliente;
import com.generation.palestra.entities.Entity;
import com.generation.palestra.entities.PianoAbbonamento;
import com.generation.palestra.entities.Scheda;

@Service
public class SchedaDao implements IDAO<Scheda> 
{
    //@Qualifier("databaseMySql")
    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    private final String insertScheda = "insert into schede(ese1, ese2, ese3, ese4, ese5, ese6, ese7, ese8, ese9, id_cliente) values(?,?,?,?,?,?,?,?,?,?)";

    private final String readAllSchede = "select * from schede";

    private final String readByIdCliente = "SELECT s.* FROM clienti c INNER JOIN schede s ON c.id = s.id_cliente WHERE c.id = ?;";

    private final String updateScheda = "update schede set ese1=?,ese2=?,ese3=?,ese4=?,ese5=?,ese6=?,ese7=?,ese8=?,ese9=? where id=?";

    private final String deleteScheda = "delete from schede where id=?";

    @Override
    public Long create(Scheda e) 
    {
        Long idScheda = database.executeUpdate(insertScheda, 
                                                            e.getEse1(),
                                                            e.getEse2(),
                                                            e.getEse3(),
                                                            e.getEse4(),
                                                            e.getEse5(),
                                                            e.getEse6(),
                                                            e.getEse7(),
                                                            e.getEse8(),
                                                            e.getEse9());
        return idScheda;
    }

    @Override
    public Map<Long, Entity> readAll() 
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readAllSchede);

        for(Entry<Long, Map<String, String>> coppia : result.entrySet())
        {
            Scheda s = context.getBean(Scheda.class, coppia.getValue());
            ris.put(s.getId(), s);
        }
        return ris;
    }

    public Scheda readById(Long id)
    {
        Map<Long, Entity> allSchede = readAll();

        return (Scheda)allSchede.get(id);
    }


    
    public Map<Long, Entity> readSchedaByIdCliente(Long idCliente)
    {
        Map<Long, Entity> ris = new LinkedHashMap<>();
        Map<Long, Map<String, String>> result = database.executeQuery(readByIdCliente, String.valueOf(idCliente));
        for(Entry<Long, Map<String, String>> coppia : result.entrySet())
        {
            Scheda s = context.getBean(Scheda.class, coppia.getValue());

            ris.put(s.getId(), s);
        }

        return ris;
    }

    

    @Override
    public void update(Scheda e, int...idModificato) 
    {
        database.executeUpdate(updateScheda, e.getEse1(),e.getEse2(),e.getEse3(),e.getEse4(),e.getEse5(),e.getEse6(),e.getEse7(),e.getEse8(),e.getEse9(), String.valueOf(e.getId()));
    }

    @Override
    public void delete(Long id) 
    {
        database.executeUpdate(deleteScheda, String.valueOf(id));
    }
}
