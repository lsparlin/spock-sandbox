package com.oreillyauto.example.web;

import java.sql.SQLException;
import java.util.List;

import com.google.common.base.Joiner;
import com.oreillyauto.example.Example;
import com.oreillyauto.example.data.ExampleDao;

public class ExampleController {

    // autowired
    private ExampleDao dao;

    public String index() {
        List<Example> list = dao.getAll();
        if (list != null && !list.isEmpty())
            return Joiner.on(", ").join(list);
        return "Empty";
    }

    public String save(Example example) {
        try {
            dao.save(example);
            return "Success";
        } catch (IllegalStateException e) {
            return "Illegal State";
        } catch (SQLException e) {
            return "SQL Error";
        }
    }

}
