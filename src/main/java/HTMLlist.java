package main.java;

// A (item of a) linked list of strings.
public class HTMLlist {
    public String str;     // Some string (a line from the source file).
    public HTMLlist next;  // Next item in the list.

    // Class constructor
    public HTMLlist (String s, HTMLlist n) {
        str = s;
        next = n;
    }
}