package main.java;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HtmlReader {

    /*
	|--------------------------------------------------------------------------
	| Public Methods
	|--------------------------------------------------------------------------
	|
	*/

    // Creates a client.HTMLlist from a file.
    public static HTMLlist readHtmlList (String filename) throws IOException {
        String name;
        HTMLlist start, current, tmp;

        // Open the file given as argument
        BufferedReader infile = new BufferedReader (new FileReader(filename));

        name = infile.readLine(); //Read the first line
        start = new HTMLlist (name, null);  // Create the first item in the list
        current = start;    // And set this as the current item
        name = infile.readLine(); // Read the next line
        while (name != null) {    // Loop as long as there are new lines
            tmp = new HTMLlist (name, null); // Create a new list item with the next line
            current.next = tmp; // Link the new list item to the current one
            current = tmp;            // Update the linked list
            name = infile.readLine(); // Read the next line
        }
        infile.close(); // Close the file

        return start;   // Return the first item in the list
    }
}
