/**
 * A StringSet is an array of strings without duplicates
 */
public class StringSet {

    //TODO: check for duplicates

    public String[] set = new String[0];

    /**
     * Adds an element to the set
     *
     * @param element
     */
    public void add(String element)
    {
        for(String item: set)
        {
            if (item.equals(element)) return; // don't add if duplicate
        }

        String[] arr1 = new String[]{element};

        set = merge(set, arr1);
    }

    public boolean isEmpty() {
        return set.length == 0;
    }

    public void append(StringSet newSet) {

        for (String item : newSet.set)
        {
            add(item);
        }

    }

    /**
     * Remove words that are not in both the newSet and this object's set
     *
     * @param newSet
     */
    public void keepDuplicatesOf(StringSet newSet)
    {
        StringSet duplicateSet = new StringSet();

        for (String item : set)
        {
            for (String item2 : newSet.set)
            {
                if (item.equals(item2)) duplicateSet.add(item);
            }
        }

        this.set = duplicateSet.set;
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
