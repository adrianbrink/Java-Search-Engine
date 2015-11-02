public class HashMap {

    private int TABLE_SIZE = 128;

    HashEntry[] table;

    public HashMap(int tableSize) {
        TABLE_SIZE = tableSize;
        table = new HashEntry[TABLE_SIZE];

        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
    }

    public UrlList get(String key) {

        int hash = (Math.abs(key.hashCode()) % TABLE_SIZE);

        HashEntry entry = table[hash];

        while (entry != null && !entry.key.equals(key)) entry = entry.next;

        return entry == null ? null : entry.value;

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
