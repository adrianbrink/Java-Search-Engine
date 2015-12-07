import org.junit.Test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class SimilarWordsTest {

    @Test
    public void it_retrieves_words_with_distance_one() throws Exception {
        LinkedHashMap<String, HashSet> hashMap = new LinkedHashMap<String, HashSet>();
        Setup.addEntryToHashMap(hashMap, "wordX", "testUrl");
        HashSet<String> similarWords = SimilarWords.retrieveSimilarWords(hashMap, "word");
        assertTrue(similarWords.contains("wordX"));
    }

    @Test
    public void it_retrieves_words_with_distance_two_if_no_words_with_distance_one() throws Exception {
        LinkedHashMap<String, HashSet> hashMap = new LinkedHashMap<String, HashSet>();
        Setup.addEntryToHashMap(hashMap, "wordXX", "testUrl");
        HashSet<String> similarWords = SimilarWords.retrieveSimilarWords(hashMap, "word");
        assertTrue(similarWords.contains("wordXX"));
    }

    @Test
    public void test_distance_algorithm() throws Exception {
        // two words that can be made identical by changing one letter will have a distance of 1
        assertTrue(SimilarWords.distance("testX", "testY") == 1);
        // two words that can be made identical by adding or removing one letter will have a distance of 1
        assertTrue(SimilarWords.distance("testX", "test") == 1);
    }
}