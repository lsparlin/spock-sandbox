package com.oreillyauto.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Joiner;

public class GuavaJoinerTest {
    private Joiner joiner;

    @Before
    public void setUp() {
        joiner = Joiner.on(", ");
    }

    @Test
    public void testBasicStringJoins() {
        String[] input = new String[] { "one", "two", "three" };
        assertEquals("one, two, three", joiner.join(input));

        input = new String[] { "", "four" };
        assertEquals(", four", joiner.join(input));

        Example example1 = new Example();
        example1.setName("First");
        Example example2 = new Example();
        example2.setName("Second");
        Example[] exampleInput = new Example[] { example1, example2 };
        assertEquals("Example [name=First], Example [name=Second]", joiner.join(exampleInput));

        input = new String[0];
        assertEquals("", joiner.join(input));
    }

    @Test
    public void testNullInputs() {
        String[] input = new String[] { "four", "five", null };

        try {
            joiner.join(input);
            fail("should catch NullPointerException");
        } catch (NullPointerException e) {

        }

        input = null;
        try {
            joiner.join(input);
            fail("should catch NullPointerException");
        } catch (NullPointerException e) {

        }
    }

}
