
public class HashEntry {
    public String key;
    public UrlList value;
    public HashEntry next;

    HashEntry(String key, UrlList value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}
