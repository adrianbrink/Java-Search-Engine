public class HashMap {

    private int TABLE_SIZE;

    HashEntry[] table;

    public HashMap(int tableSize) {
        TABLE_SIZE = tableSize;
        table = new HashEntry[TABLE_SIZE];
    }

    public UrlList get(String key) {

        int hash = (Math.abs(key.hashCode()) % TABLE_SIZE); // needs to be positive to be used as an index to an array

        HashEntry entry = table[hash];

        while (entry != null && !entry.key.equals(key)) entry = entry.next; // continue looping until we reach we end or find the key

        return entry == null ? null : entry.value; // if entry == null is true, you execute the part in front of the colon, if false the part after the colon
    }

    public void put(String key, UrlList value) {

        int hash = (Math.abs(key.hashCode()) % TABLE_SIZE);

        HashEntry entry = table[hash];

        if (entry == null) table[hash] = new HashEntry(key, value);
        else
        {
            while (entry.next != null && !entry.key.equals(key))
                entry = entry.next;

            if (entry.key.equals(key)) entry.value = value;

            else entry.next = new HashEntry(key, value);
        }
    }
}
