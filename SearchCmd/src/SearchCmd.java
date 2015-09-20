/* SearchCmd.java
   Written by Rune Hansen
   Updated by Alexandre Buisse <abui@itu.dk>
*/

import java.io.*;

class HTMLlist {
    String str;
    HTMLlist next;

    HTMLlist (String s, HTMLlist n) {
        str = s;
        next = n;
    }
}

class Searcher {

    public static boolean exists (HTMLlist l, String word) {
        while (l != null) {
            if (l.str.equals (word)) {
                return true;
            }
            l = l.next;
        }
        return false;
    }

    public static HTMLlist readHtmlList (String filename) throws IOException {
        String name;
        HTMLlist start, current, tmp;

        // Open the file given as argument
        BufferedReader infile = new BufferedReader (new FileReader (filename));

        name = infile.readLine(); //Read the first line
        start = new HTMLlist (name, null);
        current = start;
        name = infile.readLine(); // Read the next line
        while (name != null) {    // Exit if there is none
            tmp = new HTMLlist (name, null);
            current.next = tmp;
            current = tmp;            // Update the linked list
            name = infile.readLine(); // Read the next line
        }
        infile.close(); // Close the file

        return start;
    }
}

public class SearchCmd {

    public static void main (String[] args) throws IOException {
        String name;

        // Check that a filename has been given as argument
        if (args.length != 1) {
            System.out.println ("Usage: java SearchCmd <datafile>");
            System.exit (1);
        }

        // Read the file and create the linked list
        HTMLlist l = Searcher.readHtmlList (args[0]);

        // Ask for a word to search
        BufferedReader inuser =
            new BufferedReader (new InputStreamReader (System.in));

        System.out.println ("Hit return to exit.");
        while (true) {
            System.out.print ("Search for: ");
            name = inuser.readLine(); // Read a line from the terminal
            if (name == null || name.length() == 0) {
                return;
            } else if (Searcher.exists (l, name)) {
                System.out.println ("The word \""+name+"\" has been found.");
            } else {
                System.out.println ("The word \""+name+"\" has NOT been found.");
            }
        }
    }
}