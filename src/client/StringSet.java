package client;

/**
 * A StringSet is an array of strings without duplicates
 */
public class StringSet {

    //TODO: check for duplicates

    String[] set = new String[0];

    /**
     * Adds an element to the set
     *
     * @param element
     */
    public void add(String element)
    {
        String[] arr1 = new String[]{element};

        set = merge(set, arr1);
    }

    public boolean isEmpty() {
        return set.length == 0;
    }

    /**
     * Merge two Arrays
     *
     * @param arr1
     * @param arr2
     * @return
     */
    private static String[] merge(String[] arr1, String[] arr2)
    {
        int len1 = arr1.length;
        int len2 = arr2.length;

        String[] merged = new String[len1 + len2];
        System.arraycopy(arr1,0,merged,0,len1);
        System.arraycopy(arr2,0,merged,len1,len2);

        return merged;
    }


}
