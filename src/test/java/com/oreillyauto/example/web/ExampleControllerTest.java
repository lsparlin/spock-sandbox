package com.oreillyauto.example.web;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import com.oreillyauto.example.Example;

public class ExampleControllerTest {

    ExampleController controller;

    @Before
    public void setup() {
        controller = new ExampleController();
    }

    // Need to load spring context with embedded HSQLDB database
    @Test
    public void testSave() {
        Example example = new Example();
        example.setId(2);

        String response = controller.save(example);
        assertEquals("Success", response); // example 2
        
        example.setId(1); // example with id 1 already exists in HSQLDB
        response = controller.save(example);
        assertEquals("NPE", response);
        
        example.setId(-1); // example with id=-1 will throw IllegalState
        controller.save(example);
        assertEquals("Illegal State", response);
    }
}
