package main.java;

// A (item of a) linked list of strings.
public class HTMLlist {
    public String str;     // Some string (a line from the source file).
    public HTMLlist next;  // Next item in the list.
    public UrlList urls;

    // Class constructor
    public HTMLlist (String s, HTMLlist n) {
        this.str = s;
        this.next = n;
    }
}