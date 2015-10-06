package client;

import com.sun.tools.javac.util.ArrayUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;


import static org.junit.Assert.*;


public class SearcherTest {
    
    @org.junit.Test
    public void shouldReturnUrlsThatContainsKeyword() throws Exception {

        // Arrange
        HTMLlist list = new HTMLlist ("*PAGE:http://www.test.dk", null);
        HTMLlist list2 = new HTMLlist("test", null);
        HTMLlist list3 = new HTMLlist("*PAGE:http://www.test2.dk", null);
        HTMLlist list4 = new HTMLlist("test", null);

        list3.next = list4;
        list2.next = list3;
        list.next = list2;

        // Act
        StringSet result = Searcher.existsIn(list, "test");

        // Assert
        assertTrue(Arrays.asList(result.set).contains("http://www.test.dk") && Arrays.asList(result.set).contains("http://www.test2.dk") );

    }
}