package com.generation.palestra.dao.database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

//indica che questa classe è una classe di servizio, per cui all'avvio dell'applicazione
//verrà caricata e verrà creata un'istanza di essa in singleton e messa all'interno del contenitore di tutte le istanze 
//che spring avrà 
//Con @ConditionalOnProperty posso dire a Spring di istanziare o meno un BEAN in base al valore di una proprietà nel .properties
@Service
@ConditionalOnProperty(name = "database", havingValue = "mysql")
public class DatabaseMySql implements Database{

    //come faccio a prendere il valore di username dal file di configurazione
    @Value("${db.mysql.username}")
    private String username;

    @Value("${db.mysql.password}")
    private String password;

    @Value("${db.mysql.nomedb}")
    private String nomeDb;

    @Value("${db.mysql.timezone}")
    private String timeZone;

    @Value("${db.mysql.path}")
    private String path;

    private Connection connection;


    public DatabaseMySql(){}

    public void openConnection(){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(path + nomeDb +timeZone, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        return connection;
    }

    

    @Override
    public Long executeUpdate(String query, String... params) {
       openConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String[] colonne = {"id"};
            ps = connection.prepareStatement(query,colonne);
            for(int i = 0; i < params.length;i++){
                ps.setString(i+1, params[i]);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            if(rs.next()){
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }finally{
            closeConnection();
        }
        return 1L;
    }

    @Override
    public Map<Long, Map<String, String>> executeQuery(String query, String... params) {
        openConnection();
        Map<Long, Map<String,String>> righe = new HashMap<>();

        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            //select * from dipendenti where residenza = ? and iddipartimento = ?
            //parametri.length = 2
            //cella[0],cella[1]
            ps = connection.prepareStatement(query);
            for(int i = 0;i < params.length;i++){
                //scambio il ? o segnaposto che si trova nella posizione i+1 con 
                //il valore che si trova nella cella i dell'array parametri
                //per cui alla prima iterazione starò scambiando il primo ? con il valore
                //della cella 0 dell'array parametri
                ps.setString(i+1, params[i]);
            }
            //a questo punto se la query aveva dei segnaposto sono stati scambiati con i valori
            //dell'array 
            rs = ps.executeQuery();
            Map<String,String> mappaProprietà;
            while(rs.next()){
                //prendo il primo record della tabella e lo salvo in una mappa
                //la mappa conterrà tante coppie quante sono le colonne
                //le chiavi della mappa saranno i nomi delle colonne e i valori associati quelli
                //del record stesso
                mappaProprietà = new HashMap<>();
                for(int i = 1; i <= rs.getMetaData().getColumnCount();i++){
                    mappaProprietà.put(rs.getMetaData().getColumnName(i).replace("_", "").toLowerCase(), 
                                        rs.getString(i));

                }
                righe.put(rs.getLong("id"), mappaProprietà);
            }

            ps.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            closeConnection();
        }
       return righe;
    }
    

}