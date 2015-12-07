import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

/**
 * Created by Simon on 07/12/15.
 */
public class SimilarWordsTest {

    @Test
    public void test_that_retrieves_words_with_distance_one() throws Exception {

    }

    @Test
    public void test_that_if_no__words_with_distance_one_it_retrieves_with_distance_two() throws Exception {

    }

    @Test
    public void test_distance_algorithm() throws Exception {
        // two words that can be made identical by changing one letter will have a distance of 1
        assertTrue(SimilarWords.distance("testX", "testY") == 1);
        // two words that can be made identical by adding  or removing one letter will have a distance of 1
        assertTrue(SimilarWords.distance("testX", "test") == 1);
    }




}