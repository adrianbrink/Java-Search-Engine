import java.util.Arrays;

import static org.junit.Assert.*;


public class SearcherTest {
    
    @org.junit.Test
    public void shouldReturnUrlsThatContainsKeyword() throws Exception {

        // Arrange
        HTMLlist list = new HTMLlist ("test", null);

        UrlList urlList1 = new UrlList("http://www.test.dk", null);
        urlList1.next = new UrlList("http://www.test2.dk", null);
        list.urls = urlList1;

        // Act
        StringSet result = Searcher.existsIn(list, "test");

        // Assert
        assertTrue(Arrays.asList(result.set).contains("http://www.test.dk") && Arrays.asList(result.set).contains("http://www.test2.dk") );

    }
}