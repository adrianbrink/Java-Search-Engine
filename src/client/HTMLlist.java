package client;

// A (item of a) linked list of strings.
class HTMLlist {
    String str;     // Some string (a line from the source file).
    HTMLlist next;  // Next item in the list.

    // Class constructor
    HTMLlist (String s, HTMLlist n) {
        str = s;
        next = n;
    }
}