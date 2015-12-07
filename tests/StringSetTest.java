import static org.junit.Assert.*;

public class StringSetTest {

    @org.junit.Test
    public void shouldAddElementToSet() throws Exception {
        StringSet stringSet = new StringSet();

        stringSet.add("test");

        assertTrue(stringSet.set[0].equals("test"));
    }

    @org.junit.Test
    public void doesntAddDuplicates()
    {
        StringSet stringSet = new StringSet();

        stringSet.add("test");

        stringSet.add("test");

        assertTrue(stringSet.set.length == 1);
    }

    @org.junit.Test
    public void canConcatenateNewStringset()
    {
        StringSet stringSet1 = new StringSet();
        StringSet stringSet2 = new StringSet();

        stringSet1.add("test1");
        stringSet2.add("test2");

        stringSet1.append(stringSet2);

        assertTrue(stringSet1.set.length == 2);
    }

}