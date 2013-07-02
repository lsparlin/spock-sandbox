package com.oreillyauto.example.data;

import java.sql.SQLException;
import java.util.List;

import com.oreillyauto.example.Example;

public interface ExampleDao {

    public List<Example> getAll();
    
    public Example findOne(int id);
    
    public Example save(Example example) throws SQLException;
    
}
