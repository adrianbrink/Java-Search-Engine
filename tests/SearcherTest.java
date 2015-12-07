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

    @org.junit.Test
    public void itWorksWithAnAndQuery() throws Exception {
        HashMap hashMap = new HashMap(128);

        UrlList urlList = new UrlList("testUrl", null);
        hashMap.put("word1", urlList);

        UrlList urlList2 = new UrlList("testUrl2", null);
        hashMap.put("word2", urlList2);

        StringSet results = Searcher.search("word1 AND word2", hashMap);

        assertTrue(results.isEmpty());
    }

    @org.junit.Test
    public void itWorksWithAnOrQuery() throws Exception {
        HashMap hashMap = new HashMap(128);

        UrlList urlList = new UrlList("testUrl", null);
        hashMap.put("word1", urlList);

        UrlList urlList2 = new UrlList("testUrl2", null);
        hashMap.put("word2", urlList2);

        StringSet results = Searcher.search("word1 OR word2", hashMap);

        assertTrue(results.set[0].equals("testUrl") && results.set[1].equals("testUrl2"));
    }
}