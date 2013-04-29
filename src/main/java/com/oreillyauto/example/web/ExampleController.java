package com.oreillyauto.example.web;

import java.util.List;

import com.google.common.base.Joiner;
import com.oreillyauto.example.Example;
import com.oreillyauto.example.data.ExampleDao;

public class ExampleController {

    // autowired
    private ExampleDao dao;
    
    public String index() {
        List<Example> list =  dao.getAll();
        if (list != null && !list.isEmpty())
            return Joiner.on(", ").join(list);
        return "Empty";
    }
    
    public String details(int id) {
        Example example = dao.findOne(id);
        return example != null ? example.toString() : "Not Found";
    }
    
    public String post(Example example) {
        if (example != null && example.getId() == 0) {
            example = dao.save(example);
            return example.getId() + "";
        }
        return "Invalid";
    }

}
