public class UrlList {
    public String url;     // a url
    public UrlList next;  // Next url in the list

    // Class constructor
    public UrlList (String s, UrlList n) {
        url = s;
        next = n;
    }
}