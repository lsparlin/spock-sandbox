package com.oreillyauto.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GroovyLanguageAltTest {

    @Test
    public void testJavaEquality() {
        Example example1 = new Example();
        Example example2 = new Example();

        assertFalse(example1 == example2);
        assertTrue(example1.equals(example2));
    }

    @Test
    public void testJavaLists() {
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        // list.addAll(Arrays.asList(1, 2, 3, 4, 5));

        assertEquals(list, Arrays.asList(1, 2, 3, 4, 5));
        assertTrue(1 == list.get(0));
        assertTrue(5 == list.get(list.size() - 1));

    }
    
    @Test
    public void testJavaMaps() {
        Map<Object, Object> map = new HashMap<Object, Object>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put("key", "val");
        
        assertEquals(3, map.size());
        assertEquals("One", map.get(1));
        assertEquals("Two", map.get(2));
        assertEquals("val", map.get("key"));
        
    }

    @Test
    public void testJavaRanges() {
        List<Integer> range = new ArrayList<Integer>();
        int rangeStart = 1;
        int rangeEnd = 3;
        for (int i = rangeStart; i <= rangeEnd; i++) {
            range.add(i);
        }

        assertEquals(3, range.size());
        assertTrue(range.get(0) == 1);
        assertTrue(range.get(range.size() - 1) == 3);
        assertTrue(range.contains(2));
    }

}
