package com.generation.palestra.dao.database;

import java.util.Map;

public interface Database 
{

    Long executeUpdate(String query,String...params);
    Map<Long,Map<String,String>> executeQuery(String query,String...params);
    
}
