package test.java;

import main.java.StringSet;

import static org.junit.Assert.*;

public class StringSetTest {


    @org.junit.Test
    public void shouldAddElementToSet() throws Exception {

        StringSet stringSet = new StringSet();

        stringSet.add("test");

        assertTrue(stringSet.set[0].equals("test"));
    }

}