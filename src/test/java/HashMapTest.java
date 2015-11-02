import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {

    @Test
    public void testGetandPut() throws Exception {
        HashMap hashMap = new HashMap(128);

        UrlList input = new UrlList("testUrl", null);

        hashMap.put("testkey", input);

        UrlList output = (hashMap.get("testkey"));

        assertTrue(input == output);
    }
}