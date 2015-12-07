import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Created by Simon on 07/12/15.
 */
public class SearcherTest {

    @Test
    public void testSearch() throws Exception {
        // test 'simple' search
        LinkedHashMap<String, HashSet> hashMap = new LinkedHashMap<String, HashSet>();
        Setup.addEntryToHashMap(hashMap, "word", "testUrl");
        HashSet<String> results = Searcher.search("word", hashMap);
        assertTrue(results.contains("testUrl"));

        // test 'or' search
        LinkedHashMap<String, HashSet> hashMap2 = new LinkedHashMap<String, HashSet>();
        Setup.addEntryToHashMap(hashMap2, "word", "testUrl");
        Setup.addEntryToHashMap(hashMap2, "word2", "testUrl2");
        HashSet<String> results2 = Searcher.search("word OR word2", hashMap2);
        assertTrue(results2.contains("testUrl") && results2.contains("testUrl2"));

        // test 'and' search
        LinkedHashMap<String, HashSet> hashMap3 = new LinkedHashMap<String, HashSet>();
        Setup.addEntryToHashMap(hashMap3, "word", "testUrl");
        Setup.addEntryToHashMap(hashMap3, "word2", "testUrl2");
        HashSet<String> results3 = Searcher.search("word AND word2", hashMap3);
        assertTrue(results3.isEmpty());

        Setup.addEntryToHashMap(hashMap3, "word2", "testUrl");
        HashSet<String> results4 = Searcher.search("word AND word2", hashMap3);
        assertTrue(results4.contains("testUrl"));
    }
}