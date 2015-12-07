import static org.junit.Assert.*;

public class SearcherTest {
    
    @org.junit.Test
    public void shouldReturnUrlsThatContainsKeyword() throws Exception {
        HashMap hashMap = new HashMap(128);

        UrlList urlList = new UrlList("testUrl", null);
        hashMap.put("testKey", urlList);

        StringSet result = Searcher.existsIn(hashMap, "testKey");

        String output = result.set[0];
        assertTrue(output == "testUrl");
    }
}